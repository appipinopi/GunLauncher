# GunLauncher (Deep Engine Framework)

GunLauncherは、Minecraft（Spigot / Paper 1.21）向けの**次世代銃ランチャーフレームワーク（Plugin-in-Plugin形式）**です。
外部のアドオン形式（別プラグインのインストール）を廃止し、**`plugins/GunLauncher/modules/` フォルダに専用の銃モジュール（.jar）を配置するだけで、自動的に動的ロード・拡張**される「Deep」な仕組みを採用しています。

また、標準で**9種の有名銃、口径別弾薬（5種）、詳細なカスタムパーツ（4種）**が内蔵されており、導入した瞬間から本格的なタクティカルFPSを楽しめます。

---

## 📂 フォルダ構造と Plugin-in-Plugin (Deep) の仕組み

サーバーの `plugins/` フォルダには `GunLauncher.jar` を1つ配置するだけです。
起動時に以下のディレクトリが自動生成されます：

```text
plugins/
 └── GunLauncher/
      ├── config.yml
      └── modules/
           ├── custom-gun-pack-a.jar  <-- 追加したい銃モジュールをここに置くだけ！
           └── custom-gun-pack-b.jar
```

---

## 🔫 標準内蔵されている強力な銃・アイテム一覧

### 1. 口径別弾薬 (`Ammunition`)
| 弾薬ID | 名称 | 用途 |
| :--- | :--- | :--- |
| `sample:ammo_9mm` | 9mm Parabellum | サブマシンガン用 |
| `sample:ammo_556mm` | 5.56x45mm NATO | 標準アサルトライフル用 |
| `sample:ammo_762mm` | 7.62x51mm NATO | バトルライフル・スナイパー用 |
| `sample:ammo_50ae` | .50 AE | デザートイーグル用 |
| `sample:ammo_50bmg` | .50 BMG | 対物ライフル用 |

### 2. カスタムパーツ (`Attachment`)
| パーツID | スロット | 効果 |
| :--- | :--- | :--- |
| `sample:scope_x4` | `SCOPE` | ACOG 4xスコープ（射程 +20.0） |
| `sample:silencer` | `BARREL` | サプレッサー（消音・威力微減） |
| `sample:extended_mag` | `MAGAZINE` | 拡張マガジン（装弾数 +15） |
| `sample:heavy_stock` | `STOCK` | タクティカルストック（威力 +1.5） |

### 3. 内蔵有名銃 (`CustomGun`)
| 銃ID | 表示名 | 対応口径 | ModelData | 特徴 |
| :--- | :--- | :--- | :--- | :--- |
| `sample:m4a1` | M4A1 Assault Rifle | 5.56mm | `1001` | 標準アサルトライフル |
| `sample:ak47` | AK-47 Assault Rifle | 7.62mm | `1002` | 高威力アサルトライフル |
| `sample:m16` | M16 Burst Rifle | 5.56mm | `1003` | 3点バースト射撃 |
| `sample:mx7` | MX7 Submachine Gun | 9mm | `1004` | 50連発ハイテンポSMG |
| `sample:infantry_rifle` | 歩兵銃 | 7.62mm | `1005` | ボルトアクション単発銃 |
| `sample:scar_h` | FN SCAR-H | 7.62mm | `1101` | 強力バトルライフル |
| `sample:desert_eagle` | Desert Eagle | .50 AE | `1102` | ハンドキャノン拳銃 |
| `sample:awp` | AWP Sniper | 7.62mm | `1103` | 即死級スナイパーライフル |
| `sample:barrett_m82` | Barrett M82 | .50 BMG | `1104` | 圧倒的対物ライフル |

---

## 🛠️ Deepモジュール（追加銃プラグイン）の作り方

他の開発者が独自の銃を追加するモジュール（.jar）を作る場合、`GunLauncher` をビルドパスに含めて `CustomGun` を実装し、ビルドしたJARを `plugins/GunLauncher/modules/` に入れるだけで動作します（META-INFやplugin.yml等の複雑なSpigotプラグイン設定は不要です）。
