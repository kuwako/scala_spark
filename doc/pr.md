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
