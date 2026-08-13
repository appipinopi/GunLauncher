# Glock Gen5 (Grok 17) 究極の6工程・公式パーツ製造マスターマニュアル

本ドキュメントは、実銃グロックのファクトリーライン（6つの製造工程）および Sportec 社提供の **Glock Gen5 公式パーツリスト（Pos. 1 〜 34）** に完全準拠し、Minecraft エコシステム（**Gun-Craft**）において最高精度のグロックを製造するための公式リファレンスです [1]。

---

## 🏭 グロックファクトリー「究極の6工程」サプライチェーン

```text
[工程①] 炭素鋼ブロック ──► [CNC精密加工機] ──► スライド素体 ──► [nDLC処理炉] ──► 表面硬化スライド (Pos.1)
[工程②] 鋼管＋マンドレル ──► [ハンマー鍛造機] ──► バレル素体 ──► [nDLC処理炉] ──► 鍛造バレル (Pos.2)
[工程③] ポリマー樹脂 ＋ レール ──► [射出成形機(インサート)] ──► 強化フレーム (Pos.16)
[工程④] MIM金属粉末 ＆ プレス ──► [MIM焼結炉/プレス] ──► 内部精密パーツ・マガジン (Pos.4-15, 18-34)
[工程⑤] 表面硬化処理 ──► [nDLC処理炉] ──► ダイヤモンド級表面防錆・硬化
[工程⑥] ロボット組立 ＋ [銃器組立台] ＋ プルーフ弾テスト ──► 完品 Glock Gen5 本体完成！
```

### 詳細な6つの製造ステージ
1. **工程①：スライドのCNC精密削り出し**
   - 高品質な炭素鋼角棒から、CNCマシニングセンター（`STONECUTTER`）でスライドの複雑な形状、インナーレール、窓、セーフティ用の溝を高精度（ミクロン単位）に削り出します。その後、**工程⑤**のnDLC表面処理へ送られます。
2. **工程②：バレルの「冷間ハンマー鍛造」**
   - 高強度鋼の筒にポリゴナルライフリングが刻まれた超硬合金芯棒（マンドレル）を差し込み、油圧ハンマー鍛造機（`ANVIL`）で四方から激しく叩き伸ばします。金属分子密度を極限まで高め、外側をCNC旋盤で仕上げます。
3. **工程③：フレームの樹脂射出成形（インサート成形）**
   - あらかじめプレス成形されたスチールレールを射出成形機（`FURNACE`）の金型に配置。そこにグロック特有の高強度ポリマー樹脂を高圧で流し込み、一体成形します。
4. **工程④：微小パーツのMIM（金属粉末射出成形）とプレス加工**
   - トリガーバーやロッキングブロック等の複雑なパーツは、金属粉末とバインダーを混ぜて流し込み、高温で焼き固めるMIM技術（スプリング巻き機 `PISTON` 等で統合管理）で量産されます。
5. **工程⑤：究極の表面硬化処理（nDLCコーティング）**
   - スライドとバレルに特殊な熱処理と表面コーティングを施し、表面硬度をダイヤモンド級に引き上げます。これにより海水でも錆びない驚異的耐久性を獲得します。
6. **工程⑥：組立と過負荷弾（プルーフテスト）実射検査**
   - 銃器組立台（`SMITHING_TABLE`）にて、**Grok 17 の設計図** と **公式パーツ Pos.1 〜 34 のすべて**、そして出荷前テスト用の **超過負荷プルーフ弾** を投入し、ストレステストに合格した個体だけが完品となります。

---

## 📋 Glock Gen5 公式パーツリスト (Pos. 1 ～ 34)

| Pos. Nr. | Item No. | Description (公式名称) | 担当する製造機械 |
| :--- | :--- | :--- | :--- |
| **1** | 43773 | Slide G17 Gen5/FS | CNC精密加工機 ＋ nDLC処理炉 |
| **2** | 41002 | Barrel G17 Gen5 / M | ハンマー鍛造機 ＋ nDLC処理炉 |
| **3** | 33786 | Recoil spring assembled | スプリング巻き機 |
| **4** | 36618 | Firing pin | CNC精密加工機 / MIM |
| **5** | 56 | Spacer sleeve | CNC精密加工機 |
| **6** | 63 | Firing pin spring 24N (silver) | スプリング巻き機 |
| **7** | 70 | Spring cups | CNC精密加工機 |
| **8** | 33781 | Firing pin safety | CNC精密加工機 / MIM |
| **9** | 91 | Firing pin safety spring | スプリング巻き機 |
| **10** | 33774 | Extractor (Loaded Chamber Indicator) | CNC精密加工機 |
| **11** | 112 | Extractor depressor plunger | CNC精密加工機 |
| **12** | 33522 | Extractor depressor plunger spring | スプリング巻き機 |
| **13** | 2714 | Spring-loaded bearing | CNC精密加工機 |
| **14** | 33784 | Slide cover plate | CNC精密加工機 |
| **15** | 39733 | Rear sight 6,1 GMS polymer | 射出成形機 / CNC |
| **16** | 7073 | Front sight 4.1 set polymer | 射出成形機 / CNC |
| **17** | 47985 | Frame G17 Gen5 flared assembled | 射出成形機 (インサート) |
| **18** | 39543 | Magazine catch spring | スプリング巻き機 |
| **19** | 7534 | Magazine catch reversible | 射出成形機 / CNC |
| **20** | 39567 | Slide lock spring | スプリング巻き機 |
| **21** | 33706 | Slide lock | CNC精密加工機 |
| **22** | 7894 | Locking block | MIM焼結炉 / CNC |
| **23** | 47208 | Trigger mechanism housing with ejector | 射出成形機 ＋ CNC |
| **24** | 7965 | Connector 5 (dot) | CNC精密加工機 |
| **25** | 39702 | Trigger with trigger bar AMBI | MIM焼結炉 ＋ CNC |
| **26** | 47247 | Slide stop lever AMBI | プレス機 ＋ CNC |
| **27** | 8298 | Trigger pin AMBI | CNC精密加工機 |
| **28** | 1774 | Trigger housing pin SF | CNC精密加工機 |
| **29** | 7416 | Trigger housing pin MBS | CNC精密加工機 |
| **30** | 1587 | Magazine tube | プレス機 (板金加工) |
| **31** | 5233 | Follower orange | 射出成形機 |
| **32** | 33510 | Magazine spring | スプリング巻き機 |
| **33** | 1693 | Magazine insert | 射出成形機 |
| **34** | 39283 | Magazine floor plate 01 | 射出成形機 |

References:
[1] [Sportec Glock Gen5 Parts List PDF](https://www.sportec.se/Media/Glock%20Gen5%20skiss%20och%20lista.pdf)
