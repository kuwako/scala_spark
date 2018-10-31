https://spark-prs.appspot.com/

# Streaming queryで毎回同じuuidが発行されているらしい
- Uuid expression should produce different values in each execution under streaming query
  - https://issues.apache.org/jira/browse/SPARK-24896
  - https://github.com/apache/spark/pull/21854/files

# 動的なリソース確保が有効になっているバリアステージを実行した場合、ジョブ送信に失敗する
- SPARK-24954
  - core/src/main/scala/org/apache/spark/scheduler/DAGScheduler.scala
  - チェック関数入れてるだけ？

# RDDによるシャッフル + リパーティションが間違った答えを出す
- SPARK-23243
  - Shuffle+Repartition on an RDD could lead to incorrect answers
  - taskが失敗した場合はtaskのやり直しでいいけど、fetchが失敗した場合にランダムにrepartitionしてると取れるものが違うらしく、結果が変わるっぽい
    - 解決策として、ソートする方法があるが重い
    - idを振ってどのパーティションに区切るかを決めるPRが優勢のようだが、まだ解決には至っていない

# distribute by を複数のカラムで使うと、javaのcodeGeneratorの問題にあたる
- "distribute by" on multiple columns (wrap in brackets) may lead to codegen issue
- https://github.com/apache/spark/pull/22066/files
  - どうやら値の上書きがされずにループされてる？

# 開放したメモリ領域へのアクセスをしている箇所があったので確保し直す処理の追加
- [SPARK-25081][Core]Nested spill in ShuffleExternalSorter should not access released memory page (branch-2.2)
- https://github.com/apache/spark/pull/22072/files

# Hiveのパーティションが適切でなくなるパターンがある
- [SPARK-14172][SQL] Hive table partition predicate not passed down correctly
- https://github.com/apache/spark/pull/13893
  - 動的なパーティションの区切りの場合

# kafkaのキャッシュがタスクが終わる前にクローズしてしまう問題の解決
- [SPARK-21869][SS] A cached Kafka producer should not be closed if any task is using it - adds inuse tracking.
- https://github.com/apache/spark/pull/19096/files

# SSのアプリで、実行中にこけてリスタートかかったときにoffsetの値が初期値に戻ってしまう対策
- [SPARK-24462][SS] Initialize the offsets correctly when restarting a query with saved state
- コミッタ的には、リスタートしたときにoffset変わるの変じゃね？と言っていて、コケたらExceptionを投げる方のPRを進めようとしている

# カラムナファイルのパーティションの最適化
- [SPARK-24906][SQL] Adaptively enlarge split / partition size for Parq… #21868
- カラムナ型のデータ読み込み時に、かなりのムダがある
- パーティションサイズを動的に変更することで高速化を図れる

# 重複のあるjoinの際に右側の条件を自動で変更するが、その際にreferenceの変更を忘れている問題の解決
- [SPARK-25150][SQL] Rewrite condition when deduplicate Join #22318
- 

# UDTを使用する際に必要だった実装クラスなどを簡略化する
- [SPARK-7768][SQL] Revise user defined types (UDT)

# セッションとセッションの間隔によってwindow関数の結果にばらつきが出ていて、ユーザーごとにその問題に対応していることの対応
- [SPARK-10816][SS] SessionWindow support for Structure Streaming #22583

# [SPARK-25697][CORE]When zstd compression enabled, InProgress application is throwing Error in the history webui 
- zstdで圧縮する場合にアプリケーション実行中にはログが見れない。これはzstdの仕様なので、Exceptionを履くのではなく、ログを残すように変更
- https://github.com/apache/spark/pull/22689

# [SPARK-25560][SQL] Allow FunctionInjection in SparkExtensions 
- SparkExtentionsにFunctionInjectionできるようにする
    - injectParserとか
    - injectResolutionRuleとかがある
- https://github.com/apache/spark/pull/22576

# [SPARK-25353][SQL] executeTake in SparkPlan is modified to avoid unnecessary decoding. 
- SparkPlanでlimitを使った際に不要なrddのdecodeが走るのをやめさせる。処理速度の向上もする。
- https://github.com/apache/spark/pull/22347

# [SPARK-25196][SQL] Extends Analyze commands for cached tables
- analyzeコマンドがキャッシュされたテーブルも計算対象に入れられるように変更がされている
- https://github.com/apache/spark/pull/22204
- analyzeコマンドの説明
    - https://docs.databricks.com/spark/latest/spark-sql/language-manual/analyze-table.html
    - ANALYZE TABLE [db_name.]table_name COMPUTE STATISTICS [analyze_option]
    - ANALYZE TABLE [db_name.]table_name COMPUTE STATISTICS FOR COLUMNS col1 [, col2, ...]

# [SPARK-25269][SQL] SQL interface support specify StorageLevel when cache table
- キャッシュテーブルへのアクセスのオプションでstorageLevelを指定できるようにする

# [SPARK-25332][SQL] Instead of broadcast hash join ,Sort merge join has selected when restart spark-shell/spark-JDBC for hive provider
- インサートが実行された際にstatsが更新されないせいで、統計情報が正確ではなくなり、hash joinがベストなのにかかわらず、マージジョインが選択される
- 再計算の際に正確な統計情報を取得するように変更されている

# [SPARK-25740][SQL] Refactor DetermineTableStats to invalidate cache when some configuration changed
- cacheのせいで適切なjoinが選ばれないため、fallBackToHdfsとdefaultSizeInBytesの設定が変更された場合にキャッシュを無効にする

# [SPARK-25098][SQL]‘Cast’ will return NULL when input string starts/en… #22089
- 普通CAST('hoge')の先頭や末尾にスペースが入っているとnullを返すが、hiveではOKが変えるため、挙動をtrimを使い合わせた

# [SPARK-25277][YARN] YARN applicationMaster metrics should not register static metrics #22279
- startというメソッドと完全に同じソースを使用するが、静的なソースとしてJVMに扱われないようにするために追加している(?)

# [SPARK-25806][SQL]The instance of FileSplit is redundant for ParquetFileFormat
- Parquetは縦分割でFileSplitの必要がないので削除している

# [SPARK-25678] Requesting feedback regarding a prototype for adding PBS Professional as a cluster manager #22822
- PBS professionalという有名なバッチ実行管理システムがあり、現状sparkはそれに対応していないので、クラスタをSparkクラスタとPBSクラスタに分割する必要があるので、その統合をしようとしている

# [SPARK-19465][SQL] Added options for custom boolean values in CSV
- CSVファイルでのBoolean値のサポート
- コミッタは必要かどうか疑問のようだが、CSV ImportやExport時に便利なのではという議論

# [SPARK-25842][SQL] Deprecate rangeBetween APIs introduced in SPARK-21608 #22841
- 2.3で追加された関数が同様以上の役割を果たしているので、不要ということになり、rangeBetweenをdeprecatedに指定

# [SPARK-24958] Add executors' process tree total memory information to heartbeat signals. #22612
- エグゼキュータのプロセスツリー全体のメモリ情報を取得できるようにし、ドライバノードでピーク計算できるようにする 

# [SPARK-25789][SQL] Support for Dataset of Avro #22878
- Avroの型のエンコーダクラスや型のインターフェースの提供
    - Avro: AVROとはApacheプロジェクトのひとつとして開発されているデータ交換形式
        - コンパクトなバイナリで高速なシリアライズ・デシリアライズが行えるため、サーバーログなどに利用されているらしい

# [SPARK-25850][SQL] Make the split threshold for the code generated function configurable #22847
- 生成されたJava関数のソースコードがspark.sql.codegen.methodSplitThresholdを超えると、複数の小さな関数に分割される
    - thresholdは分割されるcodegenによる単一のJava関数によるソースコード長のしきい値 
        - 生成されたJava関数のソースコードがこのしきい値を超えると、複数の小さな関数に分割される 
        - 生成されるバイトコードの数はわからないので、コード長をメトリックとして使用する 
        - HotSpotで実行しているときは、8KBを超えられない。さもなければ、それはJITされない
            - HotSpot: HotSpot（ホットスポット）はオラクル（サン・マイクロシステムズ）が提供しているJava仮想マシンで使われている高速化のための技術














































