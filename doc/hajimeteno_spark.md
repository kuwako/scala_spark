# 初めてのSpark
## 3章 RDD
### 永続化
同じRDDを複数回使う場合、Sparkは依存対象を再計算してしまうのでpersist(), unpersist()でメモリ上に適切にキャッシュしておくことが大切  
unpersist()をし忘れるとメモリ上に保持され続け、後続の処理を圧迫してしまうので注意

## 4章 キー/値ペアの処理
### データのパーティショニング
前提: 分散プログラムでは、通信のコストが最も高いことが多い  
→ partitionBy()をうまく活用し、通信回数を減らす必要がある  

パーティショニングの恩恵を受けられるのは
- cogroup()
- join系
- groupByKey()
- reduceByKey()

などのキーを変更しない操作  
(= パーティショニングはキーを軸に行われるため)  

mapを使用すると、キーを維持して居るかsparkは判断できないため、パーティショナは崩れる  
- パーティショナを維持してmap処理をする場合はmapValues()やflatMapValue()のような、キーを維持していることを証明できるメソッドを利用する必要がある  
- カスタムパーティショナを自分で作れば、より効率の良いパーティションが実現できるが、パーティションごとにデータ量が偏ると全体に影響が出てしまうので注意が必要

## 6章 Sparkの高度なプログラミング
### アキュムレータ
- ワーカーノード群からドライバプログラムへ値を集約する
- 正しい値がわかるのは、saveAsTextFile等のアクションが行われてmap処理等が実行されたあとになる