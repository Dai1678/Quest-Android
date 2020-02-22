# Quest-Android
2019年度卒業研究プロジェクト Androidアプリ  

## Features
- 受検者の登録
- アンケートの回答・結果送信

## Contributing
このリポジトリでは受け付けていません。  
研究室の後輩がプロジェクトを引き継ぐ場合は、Forkして利用してください。  
(その際にこのリポジトリへPullRequestを作る必要はありません)  

## Requirements
Android Studio 3.5 and higher.

## Development Environment
### MVVM Architecture
Googleの推奨するMVVM + Repositoryパターンを使用。  
これらが理解できないと開発は難しいです。

### Server
受検者情報と回答情報はローカルサーバー([Quest-Server](https://github.com/Dai1678/Quest-Server))で管理しています。  
Firebase Realtime Databaseは回答ログを保存します。  

## Library
下記ライブラリを使用して開発を行っています。
- Android Jetpack(Google)
  - Foundeation
    - AppCompat
    - Android KTX
  - Architecture
    - Data Binding
    - Lifecycles
    - LiveData
    - Navigation
  - UI
    - Fragment
    - ConstraintLayout
    - RecyclerView
- Kotlin (JetBrains)
  - Stdlib
  - Coroutine
- Firebase(Google)
  - Realtime Database
- Material Components for Android (Google)
- OkHttp(Square)
  - Android Client
  - LoggingInterceptor
- Moshi(Square)
- Groupie (lisawray)
