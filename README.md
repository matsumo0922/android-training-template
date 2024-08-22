# Android研修

業務に近いかたちでアプリ開発を行いながら、
Androidアプリ開発の基礎復習・実務スキルを身に付けるための研修です.  

このAndroidプロジェクトは[android-training-template](https://github.com/yumemi-inc/android-training-template)からセットアップされました.  
課題の詳細や進め方は元のリポジトリのREADMEを参照してください.

# アーキテクチャ
アプリのアーキテクチャ図です。一部現在は実装されていないモジュールが存在しますが、今後の課題で実装される予定です。

```mermaid
%%{
init: {
'theme': 'neutral'
}
}%%

graph LR
  subgraph gradle 
    build-logic  
  end  
  subgraph application
    app  
  end  
  subgraph core
    common
    datasource
    model
    repository
    ui
  end
  subgraph feature
    top
    detail
  end
  app --> top
  app --> detail
  top --> ui
  top --> repository
  detail --> ui
  detail --> repository
  ui --> model
  repository --> datasource
  datasource --> model
  model --> common
```
