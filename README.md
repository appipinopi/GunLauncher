# GunLauncher (Core Framework)

GunLauncherは、Minecraft（Spigot / Paper 1.21）向けの**銃追加ランチャーフレームワーク**です。
GunLauncher単体では銃は存在せず、**口径別弾薬・カスタムパーツ・有名銃本体を追加する外部プラグイン（アドオン）**を導入することで、本格的なタクティカルFPS環境を構築できます。

---

## 📦 追加されたサンプルアイテム・銃一覧

同梱されているサンプルアドオンでは、以下の豊富なアイテムと銃が登録されます。

### 1. 口径別弾薬 (`Ammunition`)
| 弾薬ID (`id`) | 名称 | 説明 |
| :--- | :--- | :--- |
| `sample:ammo_9mm` | 9mm Parabellum | ハンドガンやサブマシンガン用 |
| `sample:ammo_556mm` | 5.56x45mm NATO | 標準的なアサルトライフル用 |
| `sample:ammo_762mm` | 7.62x51mm NATO | バトルライフル・スナイパー用 |
| `sample:ammo_50ae` | .50 AE | デザートイーグル用強力弾 |
| `sample:ammo_50bmg` | .50 BMG | 対物ライフル用超強力弾 |

### 2. カスタムパーツ (`Attachment`)
| パーツID (`id`) | スロット | ボーナス効果 |
| :--- | :--- | :--- |
| `sample:scope_x4` | `SCOPE` | 射程 +20.0 (ACOG 4倍スコープ) |
| `sample:silencer` | `BARREL` | 威力 -1.0 (タクティカルサプレッサー) |
| `sample:extended_mag` | `MAGAZINE` | 最大弾数 +15 (拡張マガジン) |
| `sample:heavy_stock` | `STOCK` | 威力 +1.5 (タクティカルストック) |

### 3. 有名銃・カスタム銃 (`CustomGun`)
| 銃ID (`id`) | 表示名 | 対応口径 | CustomModelData | 特徴 |
| :--- | :--- | :--- | :--- | :--- |
| `sample:m4a1` | M4A1 Assault Rifle | 5.56mm | `1001` | 標準アサルトライフル |
| `sample:ak47` | AK-47 Assault Rifle | 7.62mm | `1002` | 高威力アサルトライフル |
| `sample:m16` | M16 Burst Rifle | 5.56mm | `1003` | 3点バースト射撃 |
| `sample:mx7` | MX7 Submachine Gun | 9mm | `1004` | 50連発ハイテンポSMG |
| `sample:infantry_rifle` | 歩兵銃 | 7.62mm | `1005` | ボルトアクション単発銃 |
| `sample:scar_h` | FN SCAR-H | 7.62mm | `1101` | 強力なバトルライフル |
| `sample:desert_eagle` | Desert Eagle | .50 AE | `1102` | ハンドキャノン拳銃 |
| `sample:awp` | AWP Sniper | 7.62mm | `1103` | 即死級スナイパーライフル |
| `sample:barrett_m82` | Barrett M82 | .50 BMG | `1104` | 圧倒的対物ライフル |

---

## 🕹️ コマンド一覧
- `/gun give <player> <gunId>` - 銃を配布
- `/gun ammo <player> <ammoId> [amount]` - 弾薬を配布
- `/gun part <player> <partId>` - パーツを配布
- `/gun list` - 登録されているすべての銃・弾薬・パーツの一覧を表示
