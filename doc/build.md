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
