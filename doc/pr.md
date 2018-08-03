# Streaming queryで毎回同じuuidが発行されているらしい
- Uuid expression should produce different values in each execution under streaming query
  - https://issues.apache.org/jira/browse/SPARK-24896
  - https://github.com/apache/spark/pull/21854/files

# 動的なリソース確保が有効になっているバリアステージを実行した場合、ジョブ送信に失敗する
- SPARK-24954
  - core/src/main/scala/org/apache/spark/scheduler/DAGScheduler.scala
  - チェック関数入れてるだけ？

# RDDによるシャッフル + リパーティションが間違った答えを出す
- Shuffle+Repartition on an RDD could lead to incorrect answers
- 
