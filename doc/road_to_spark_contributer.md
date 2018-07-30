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
1. もし変更が新規ののである場合、新規のJIRAが必要です。しかし、例えばtypoなどの大したことのない変更の場合はJIRAは必要ありません
1. 必要であれば、JIRAを作ってください
  1. 説明的なタイトルにしてください。「WEB UIのアップデート」、「スケジューラの問題」などは不十分です。
  1. 詳細な説明を書いてください。バグレポートでは再現方法の短い記述、新機能ではデザインドキュメントを含むと良いでしょう。
  1. 必要事項を記入する
    1. Issue Type: Sparkでは一般にバグ、改善、新機能のみが使われます
    1. Priority: Major か belowを選択してください。「高優先度」はコミッタのみがセットできます
    1. JIRAはPriorityフィールドの値にサイズと重要どうを感れ付ける傾向があります。
        1. Blocker: この変更を加えずにリリースすることは無意味です。ユーザーの多数がこのリリースを使用できなくなるためです。
        1. Critical: この機能なしだと多数のユーザーが重要な機能を使えない、または回避するのは難しい
        1. Major: この機能がないと少数のユーザーが重要な機能を使えないまたは回避することは可能
        1. Minor: ニッチなケースでサポートがなくなるが、使い方に影響を及ばさない or 回避するのが容易
        1. Trivial: あったら良い変更だが、なくても問題がない  
    1. Component(成分)
    1. Affects Version: バグについては、問題が発生していることがわかっている、または変更が必要なバージョンを少なくとも1つ割り当ててください
  1. 次のフィールドは設定しないでください
    1. Fix Version: この領域はコミッタが入力します
    1. Target Version: この領域はコミッタが入力します
  1. パッチファイルを含まないでください。プルリクエストは実際の変更を提案するために使用されます。
1. 変更が大きい場合はdev@spark.apache.orgで変更に関するディスカッションを行ってください

### Pull Request
1. Githubリポジトリをフォークしてください https://github.com/apache/spark (まだ行っていない場合のみ)
1. フォークしたものをクローンしてブランチを作成し、コミットはそのブランチに行ってください
1. 変更に対してドキュメントやテストが必要でないか検討し、必要であれば追加してください
1. ./dev/run-tests でテストをすべて実行し、ステベ手のコンパイルやスタイルチェックが通るか確認してください
1. masterブランチに対してプルリクエストをopenしてください（特殊な場合に限ってのみmaster以外のブランチにプルリクエストを作ってください）
  1. PRのタイトルは[SPARK-xxxx][COMPONENT] TITLEの形式で、xxxxの箇所にはJIRAナンバーを入れ、COMPONENTの箇所にはPRのカテゴリ、TITLEの箇所にはJIRAのタイトルかさらにわかりやすい内容を入れると良い
  1. もしPRが作業中でマージできないが、レビューを促進したい場合は[WIP] をcomponentのあとに追加してください
  1. 特定のコミッタやコントリビュータを探すことを検討してください。githubのblameを使えば誰が最後に編集したかを教えてくれます
  1. 作成したソースがあなた自身の著作物であることを約束し、そのソースはオープンソースとして管理されることを覚悟してください
1. 関係するJIRAはIn Progressとしてマークされ、あなたのプルリクエストは自動的にひも付けられるでしょう。アサイニーになる必要はありませんが、もし引き受ければ歓迎されるでしょう
1. jenkinsの自動プルリクエストビルダがあなたの変更をテストします
  1. もしそれがあなたの最初のContributeであったなら、Jenkinsはビルドを待って、 “Can one of the admins verify this patch?”と投稿します
  1. コミッタはok to testとコメントし、テストを許可します
  1. コミッタは"Jenkins, add to whitelist"とコメントすることで自動的に許可将来的なプルリクエストのテストを許可することができます
1. 約2時間後、Jenkinsはプルリクエストのテスト結果をポストします
1. 結果を確認し、迅速に問題を解決してください
  1. 修正はPRと同じブランチにpushできます
  1. JenkinsはPushされると自動的に再度テストを実行します
  1. テストが変更に関係しない理由で失敗した場合（例えばJenkinsの停止など）、コミッタは「Jenkins, Restart this」で再テストを要求することができます。
1. SparkRに関わる変更がPRに存在する場合は、AppVeyorが自動的にWindows上でSparkRをテスト実行します。これには約1時間かかります。

### レビュープロセス
- コミッタに含まれる他のレビュワーが変更に対してコメントし、修正案を提案するかもしれません
- 活発で、礼儀正しく、迅速な技術的議論がコミュニティの皆さんから奨励されています。その結果、変更すべてが全体の拒絶されるかもしれない
- 良さそうだったらLGTMつくよ
- Conflictしたら手動で解決してください
- 議論にはできるだけ早く返信できるよう心がけてください

### プルリクエストやJIRAを閉じる
- 変更が受け入れられたら、マージされて自動的にPRもクローズされ、関連するJIRAも閉じられます
  - まれに、プルリクエストを開くように要請される場合があり、その場合は手動で閉じることがあります
  - JIRAは、クレジットを提供する方法として、変更の主な貢献者に割り当てられます。JIRAが閉鎖されていない場合や、JIRAが速やかに割り当てられていない場合は、JIRAにコメントしてください。
- プルリクエストが最終的に却下された場合は、すぐにクローズしてください
  - コミッタはPRを直接閉じることができないたm
  - コミッターが「mind closing tyhis PR？」とコメントした場合、Apacheの自動プロセスによってプル要求が自動的に閉じられます。これは、コミッターがクローズするように特に要求していることを意味します。
- もし、数日間PRにほとんど反応がなかった場合、説明か変更を改善し、レビュワーに通知することを検討してください。依存する箇所の変更などはできるだけ小さく、変更を取り込みやすいように検討してください。
- もし、レビューされたのに数週間取り込まれなければ、最も関連性の高い査読者からのレビューを求めた後、または中立的な反応を経験した場合、その結果は「soft no」とみなされている可能性がある。この場合、PRを取り消すのが良さそうです。
- PRがJIRAを解決する正しい方法ではないと判断されたためにクローズされた場合は、JIRAを開いたままにしておきます。しかし、JIRAで特定された問題がPRによって解決されないことが明らかになった場合（問題ではなく、修正の必要がない場合）、JIRAも閉じられます。

## CodeStyleGuide
既存のコードのスタイルを守ってください
- PythonにおいてはApache Sparkは1つの例外を除いてPEP8に準拠しています。(例外: １行100文字で、79ではない)
- RにおいてはApache SparkはGoogle's R Style Guideに従います。例外は3つです。１行は100文字です。80文字ではありません。関数名に制限はありませんが、キャメルケースです。S4オブジェクト/メソッドが許可されています。
- Javaコードに関してはApache Spark はOracle’s Java code conventionsに従います。多くのScalaガイドラインにJavaも従います
- Scalaに関してはApache SparkはオフィシャルのScala style guideに従います。しかし、以下の変更に追従します。

### Line Length
1行の最長は100文字です。唯一の例外はimport文です（ただし、それでも100文字以下にできるならしてください)

### インデント
2spaceインデントを使ってください。関数宣言の場合、変数が1行に収まらない場合のみ4spaceインデントを用いてください

### コードドキュメントスタイル
クラス、オブジェクト、メソッドの前にJava Docs Styleで書き込んでください
```Scala
/** This is a correct one-liner, short description. */

/**
 * This is correct multi-line JavaDoc comment. And
 * this is my second line, and if I keep typing, this would be
 * my third line.
 */

/** In Spark, we don't use the ScalaDoc style so this
  * is not correct.
  */
```
インラインコメントでは//を使用します

```scala
// This is a short, single line comment

// This is a multi line comment.
// Bla bla bla

/*
 * Do not use this style for multi line comments. This
 * style of comment interferes with commenting out
 * blocks of code, and also makes code comments harder
 * to distinguish from Scala doc / Java doc comments.
 */

/**
 * Do not use scala doc style for inline comments.
 */
```
