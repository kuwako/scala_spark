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
















































a
