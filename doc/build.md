# まずビルドして動かしてみる
## javaがないと言われる
- home/vagrant/dev/spark/build/sbt-launch-lib.bash: line 85: java: command not found
- sudo yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel 
    - https://qiita.com/Esfahan/items/60cf425514c66553bd42
## sbtインストール
- すごい時間かかった

## とりあえずDeveloper Toolsにあるコマンド叩いてみる 
- build/sbt clean package
    - すごい量のなにかをDLしている.....
    - 一時間以上かかったけど動いた

## compileできた
- build/sbt compile
- Assembly JARを再生成せずにビルドするには export SPARK_PREPEND_CLASSES=trueを環境変数に追加する必要がある

## コマンドの起点どこ？
- こいつっぽい?
    - spark/launcher/src/main/java/org/apache/spark/launcher/Main.java
        - この中で条件分岐してやってるっぽい
        - spark/core/src/main/scala/org/apache/spark/deploy/SparkSubmit.scala
        - でもなんかpythonとSparkShellの実行への分岐しかない...?
        - scala側でextends Appとかdef mainしてるところないのでやっぱり正しいかも
        - コマンドをパースし終えたらbashCommandBuilderになんか突っ込んでやってる
        - ただ、それをsystem.out.print()してるだけ...?
        - これで実行できるのかな...調べる
        - spark-classの実行内容だった

## spark-submitコマンドのオプション思い出す
- https://www.task-notes.com/entry/20160103/1451810637
- http://www.ne.jp/asahi/hishidama/home/tech/scala/spark/submit.html

## apache.spark.internal.Logging
- なんかSparkSubmitとかで継承されててただのLoggingなのか気になったので調べる
    - 本当にLog関連のことしかやってない

## spark-submit
- spark/core/src/main/scala/org/apache/spark/deploy/SparkSubmit.scala の submitがメインっぽい

## prepareSubmitEnvironment
- supark-submit時に叩かれる
- @return a 4-tuple:
    - (1) the arguments for the child process,
    - (2) a list of classpath entries for the child,
    - (3) a map of system properties, and
    - (4) the main class for the child
- Keytab...?
    - yarn/local/mesosClientのときにkeytabが有効かどうかの確認が入っている
    - HiveMetastoreを使う際に必要らしい
- 実行モード(yarnとかlocalとか)ですごいたくさん分岐あって追いきれないけどyarnが一番分岐してやることが多い?
- 読み終わった
    - 分岐多すぎてグシャグシャ過ぎたのでリファクタリングできそう感あった

## doRunMain
- proxyUser
    - UserGroupInformation.createProxyUser(args.proxyUser,UserGroupInformation.getCurrentUser())
    - ってあるけど、そもそもソース上検索してもそんなオブジェクトもメソッドもなくてわからん...
- メソッドの説明に下記のようにあるけど、意味がよくわからない...
    - このメインクラスは、クラスターデプロイメントモードまたはPythonアプリケーションを実行している場合、ユーザーが提供するものではありません。
- 引数に受け取ったクラスをSparkAppとして格納し、startメソッドにパラメータを渡し、実行開始
    - リフレクションを利用してmainメソッドを呼び出して実行
        - https://qiita.com/neko_the_shadow/items/bbca2c6387cf25de251e
- 次はサンプルソースを見てメソッドの行方を追う

## SparkApplication start()
- 標準のJavaクラスを「メイン」メソッドでラップするSparkApplicationの実装。
- 設定はシステムプロパティを介してアプリケーションに伝播されるため、同じJVM内でこれらの複数を実行すると、設定漏れのために未定義の動作につながる可能性がある

# SparkSession
- 次はこいつ見ていく

## builder
- getOrCreate
    - 既存のものがあればそれを返す
    - なければ作成し、Global defaultとして設定する
        - 中でSparkContextをgetOrCreateしてnewしている

## sparkContext
- RDD作ったり、accumulatorsを作ったり、クラスタにbroadcastしたりしてる
- fileのロードとかもこの辺で実装されてる

## dataframeの実体がない...?

## SparkSqlExample読んだ

## datasetのソース読み
### datasetの説明文和訳
データセットは、ドメイン固有のオブジェクトの強く型付けされたコレクションであり、変換することができます  
関数または関係演算を使用して並行して実行されます。  
各データセットには、[[Row]]のデータセットである「データフレーム」と呼ばれるタイプのないビューもあります。  


データセットで使用できる操作は、変換とアクションに分かれています。  
変換は新しいデータセットを生成するものであり、アクションは計算と結果のトリガとなるものです。  


変換の例には、map、filter、select、およびaggregate（ `groupBy`）があります。  

アクションの例は、ファイルシステムにデータをカウント、表示、または書き込むアクションです。  

データセットは「遅延的」です。つまり、アクションは、アクションが呼び出されたときにのみトリガされます。  

内部的には、データセットは、データを生成するために必要な計算を記述する論理プランです。  

アクションが呼び出されると、Sparkのクエリオプティマイザは論理プランを最適化し、パラレルで分散して効率的に実行するための物理プランを生成します。  
論理計画と最適化された物理計画を調べるには、 `explain`関数を使用します。  

ドメイン固有のオブジェクトを効率的にサポートするには、[[Encoder]]が必要です。  
エンコーダは、ドメイン特有のタイプ「T」をSparkの内部タイプシステムにマッピングする。
たとえば、 `name`（文字列）と` age`（int）という2つのフィールドを持つ `Person`クラスを与えられたエンコーダは、` Person`オブジェクトをバイナリ構造にシリアル化するために、実行時にコードを生成するようにSparkに指示する。
このバイナリ構造は、しばしば、メモリフットプリントがずっと小さく、データ処理（例えば、円柱フォーマット?）の効率のために最適化されている。
データの内部バイナリ表現を理解するには、 `schema`関数を使用してください。  

通常、データセットを作成するには2つの方法があります。  
最も一般的な方法は、SparkをSparkSessionで利用できる `read`機能を使って、ストレージシステム上のいくつかのファイルに向けてSparkを指すことです。  

## sql
### sessionState.sqlParser.parsePlan(sqlText)
- こいつでsql文をパースしてsqlライクの処理を実行可能にしている
-  A class that holds all session-specific state in a given [[SparkSession]].
    - @param sqlParser Parser that extracts expressions, plans, table identifiers etc. from SQL texts.
- parseの実体はSparkSessionExtensionSuiteのparseっぽい..?
    - 中でSqlBaseParser使ってる
- AbstractSqlParserのparseに実体っぽい

## mapValues
- KeyValueGroupedDatasetのmapValueを追い中

### ロジカルプランってどうやって作ってる...?
- QueryExcutionクラスでanalyzeしてる...?
- 実行はあとのタイミングでまとめてやってるっぽい
    - RuleExecutorのexecuteに渡ってる

## 通常のdatasetのmap
- withTypedPlanが使われている
- Object.scala MapElementでロジカルプランを決定してる
- CatalystSerdeでdeserializeされて、後ろでserializeして返している
    - UnaryNodeというロジカルプランを拡張したオブジェクトに値を渡している
