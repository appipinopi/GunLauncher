# Glock Gen5 (Grok 17) 公式パーツ準拠 製造マスターマニュアル

本書は、Sportec社提供の **Glock Gen5 公式分解図およびパーツリスト（PDF）** に準拠し、Minecraft 銃撃戦エコシステム（**Gun-Craft**）において最高精度のグロックを製造するための公式マニュアルです [1]。

---

## 🏭 製造サプライチェーンの全体像

Grok 17 (Glock Gen5) の完成には、公式リストに基づく**150個以上の個別パーツ**および**精密ネジ**が必要です。

```text
[原材料: 鉄塊 / 鋼線 / ポリマー / 真鍮]
       │
       ├─► [スプリング巻き機] ──► リコイルスプリング、各スプリング類
       ├─► [CNC精密加工機]   ──► スライド、バレル、トリガー、ピン、精密ネジ
       ├─► [射出成形機]       ──► Frame G17 Gen5（ポリマーフレーム）
       ├─► [ハンマー鍛造機]   ──► Match-Grade Barrel（銃身）
       │
       └─► [銃器プリント台]   ──► Glock Gen5 設計図（プリント済みの紙）
              │
              ▼
    [銃器組立台 (150パーツ + 設計図 + 精密ドライバー)] ──► Glock Gen5 本体完成！
```

---

## 📋 Glock Gen5 公式パーツ・製造機械 対応一覧表

| Pos. Nr. | 公式アイテム名称 (Glock Gen5 Spare Parts) | 製造機械 (Required Machine) | 必要素材 |
| :--- | :--- | :--- | :--- |
| **1** | Slide G17 Gen5/FS (スライド) | CNC精密加工機 (`STONECUTTER`) | 鉄インゴット x2 |
| **2** | Barrel G17 Gen5 / M (バレル) | ハンマー鍛造機 (`ANVIL`) | 鋼材 / ネットライト |
| **3** | Recoil spring assembled (リコイルスプリング) | スプリング巻き機 (`PISTON`) | 鋼線 x2 |
| **4-7** | Firing pin assembled (ファイアリングピン周辺) | CNC精密加工機 / 巻き機 | 鉄・鋼材各種 |
| **8** | Firing pin channel liner (チャンネルライナー) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **9** | Firing pin safety assembled (FPセーフティ) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **10** | Extractor Loaded Chamber Indicator (エキストラクター) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **11-12** | Extractor depressor plunger & bearing | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **13** | Slide cover plate (スライドカバープレート) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **14-15** | Rear & Front sights (サイトセット) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x2 |
| **16** | Frame G17 Gen5 flared assembled (フレーム) | 射出成形機 (`FURNACE`) | 黒コンクリート/樹脂 |
| **17** | Beavertail set (ビーバーテイルセット) | 射出成形機 (`FURNACE`) | ポリマー素材 |
| **18-19** | Magazine catch & spring (マガジンキャッチ) | CNC精密加工機 / 巻き機 | 鉄・鋼線各種 |
| **20-21** | Slide lock & spring (スライドロック) | CNC精密加工機 / 巻き機 | 鉄・鋼線各種 |
| **22** | Locking block (ロッキングブロック) | CNC精密加工機 (`STONECUTTER`) | 鉄インゴット x1 |
| **23** | Trigger mechanism housing with ejector (ハウジング) | CNC精密加工機 (`STONECUTTER`) | 鉄インゴット x1 |
| **24** | Connector 5 (dot) (コネクター) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **25** | Trigger with trigger bar AMBI (トリガーバー) | CNC精密加工機 (`STONECUTTER`) | 鉄インゴット x1 |
| **26** | Slide stop lever AMBI (スライドストップ) | CNC精密加工機 (`STONECUTTER`) | 鉄インゴット x1 |
| **27-28** | Trigger & housing pins (各種ピン) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x2 |
| **29-32** | Magazine assembly (マガジン構成部品) | CNC精密加工機 / 巻き機 | 鉄・鋼線・樹脂 |
| **33-60** | Gen5 Precision Screws (0.5mm ~ 2.0mm) | CNC精密加工機 (`STONECUTTER`) | 鉄ナゲット x1 |
| **61-150**| Gen5 Internal Precision Components | CNC精密加工機 / 巻き機 | 鉄/鋼材各種 |

---

## 🛠️ 組み立て時の注意事項
1. **公式規格の遵守**: Glock Gen5 のパーツは従来モデルと互換性がありません [1]。組立時は必ず Gen5 専用パーツ（ID #1〜#150）を使用してください。
2. **工具とネジ山**: 精密ネジの締結には適合するミリサイズの精密ドライバーを使用してください。

References:
[1] [Sportec Glock Gen5 Parts List PDF](https://www.sportec.se/Media/Glock%20Gen5%20skiss%20och%20lista.pdf)
