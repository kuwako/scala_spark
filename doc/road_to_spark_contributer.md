# Sparkコントリビュータになるためのメモ
- [Apache Spark Community](https://spark.apache.org/community.html)

# MailingLists & Resources
## 基本方針
- 既存の質問を調べる
    - [stack overflow](https://stackoverflow.com/questions/tagged/apache-spark)
    - [Apache Spark User List](http://apache-spark-user-list.1001560.n3.nabble.com/)

## メーリングリスト
- user@spark.apache.org is for usage questions, help, and announcements
- dev@spark.apache.org is for people who want to contribute code to Spark.
- issues@spark.apache.org to receive emails about new issues
- commits@spark.apache.org to get emails about commits

## チャットルーム
- https://gitter.im/spark-scala/Lobby?source=orgpage

## Meetup
- [Tokyo Spark Meetup](https://www.meetup.com/ja-JP/Tokyo-Spark-Meetup/)

## 課題管理
- [JIRA](https://issues.apache.org/jira/projects/SPARK/issues/SPARK-14220?filter=allopenissues)

# Contributing to Spark
## 他者のヘルプによるContribute
- メーリングリストへの返信がコミュニティへの価値になる

## テストリリースによるContribute
- Sparkコミュニティのリリースはコミュニティ内の投票によって行われる
- 新しいリリースをテストし、パフォーマンスや正確性の問題についてフィードバックする

## レビューによるContribute
- 提出されたpull requestに対して、コメントや質問をすることでも貢献できる
- pull requestの状態は[ここ](https://spark-prs.appspot.com/)

## ドキュメントの更新によるContribute
- [ドキュメント](https://spark.apache.org/docs/)の更新をmarkdownで行う

## ユーザーライブラリへのContribute
- たくさん存在する既存のjava/scalaライブラリにSparkを対応させたい
- spark-packages.orgにあるもので改善できるものがあったらよろしく

## バグレポートによるContribute
- バグフィックスも一緒に投げてくれると嬉しい
- いつでも可能なわけではないのでその時はJIRA作ってください
- まずはメーリングリストなどに投げてください
- 再現性のないものはcloseされます

## JIRAメンテナンスによるContribute
- 問題を解決したことが指摘できる変更がある場合は修正済みとしてください
- 問題が別の問題のサブセットである場合は、重複としてください
- 問題がはっきりと時代遅れになり、開かれてから根本的に変化した問題やコンポーネントに適用される場合は、問題ではないとして解決してください

## コードの変更にContributeする準備
### 何にCotributeするかを選ぶ
sparkでは平均して2,3時間ごとに新しいJIRAやpull requestが作成される。  
レビューにはコミッタの時間が数時間から数日かかります  
もしContributerが便利で、明快で、簡単に評価できて、基本的なチェックが終わっている変更に注力すれば、みんなの利益になります  


時には、Contributerがすでにやってほしいことをリスト化している可能性があるので、JIRAのタスクを確認するか、メーリングリストに尋ねてください  
事前に以下のチェックリストを確認してください
- コードを変更する必要が本当にありますか？JIRAへの提案やプルリクエストは本当に必要なときだけ行うべきです
- 関連する議論に関しては過去のメーリングリストを探してください。以前にメーリングリスト上の議論で解決済みかもしれません
- 変更するコードは十分な経験を積んだ上でのものですか？ typoなら誰でも直せますが、core scheduling logic のリファクタリングは相応のsparkの理解と経験が必須です

### MLlib固有のContributeガイドライン
豊富なアルゴリズムは重要ですが、プロジェクトのスケールのためにメンテナンス性、継続性、ソースの品質が第一です  
新規アルゴリズムは以下の要素を満たしてください
- 幅広く知られていること
- 実際に利用されていること
- スケーラビリティが高いこと
- 十分にドキュメントが書かれること
- 他のMLLibとAPIの様式が一貫されていること
- デベロッパーサポートができること(?)
- @Since アノテーションをpublic class、method, variableにつけること

### コードレビュー基準
コードのContributeの前に、どのようにコードがレビューされ、なぜ変更が拒否されるのかの文脈を理解しておくことは重要です  
簡単に言うと、変更がポジティブな影響をもたらし、ネガティブな影響やリスクが少ないならばマージされる確率は高く、迅速にマージされるだろう  
リスキーで価値の少ない変更はマージされる確率が低く、レビューを繰り返し受けるのではなく、リジェクトされることもあるだろう  

#### ポジティブ
- バグの存在する機能を根本的に解決する
- 多くのユーザーが使う機能を追加/修正
- シンプルで絞られている
- Python, Java, Scalaでの一貫性の維持
- 簡単にテストされていることと、テストを持っていること
- コードの複雑性と行数の削減
- 変更が議論済みで、コミッターにも知られていること

#### ネガティブ/リスク
- バンドエイド(１つのバグに処置するだけの場当たり的な修正)
- 複雑な新機能の導入、特に継続的にサポートが必要なAPI
- ニッチなユーザーに対応するためだけに複雑性を追加すること
- Spark内で管理する必要のないuser-space機能
- パブリックなAPIやセマンティックの変更(ほとんど許可されることはない)
- 大きな依存の追加
- 既存の依存(ライブラリ?)のバージョン変更
- 大量のコード
- 1つの変更でビッグバンのような変更を行うこと

## コード変更によるContribute
投稿したコードは全てApache/Sparkのオープンラインセンスの元に置かれるのでよろしくね  
最新のコードはここからcloneしてね
```
# Master development branch
git clone git://github.com/apache/spark.git
```

### JIRA
一般に、SparkはJIRAを使用してバグや改善などの論理的な問題を追跡し、Githubのプルリクエストを使用して特定のコード変更のレビューとマージを管理します  
つまり、JIRAは何を修正または変更するべきかを記述するために使用され、高水準のアプローチやpull requestでは、その変更をプロジェクトのソースコードに実装する方法が記述されています  
たとえば、主要な設計上の決定はJIRAで議論されています

1. 変更の関係する既存のJIRAを検索してください
  1. 既存のJIRAで議論されているissueに対して、新しいJIRAを作成しないでください
  1. 既存のpull requestと重複しないように、JIRAに紐付けられているpull requestを確認してください
