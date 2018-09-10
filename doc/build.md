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
