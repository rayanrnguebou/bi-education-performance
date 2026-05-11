# 📊 Analyse des Indicateurs de Performance dans le Secteur de l'Éducation

> Projet de Business Intelligence complet — Entrepôt de données, pipeline ETL et tableaux de bord interactifs Power BI pour le suivi des performances académiques.

---

## 📋 Table des Matières

- [Aperçu du Projet](#aperçu-du-projet)
- [Stack Technique](#stack-technique)
- [Architecture](#architecture)
- [Données](#données)
- [Pipeline ETL](#pipeline-etl)
- [Dashboards Power BI](#dashboards-power-bi)
- [Indicateurs Clés (KPIs)](#indicateurs-clés-kpis)
- [Structure du Repo](#structure-du-repo)
- [Installation et Configuration](#installation-et-configuration)
- [Auteur](#auteur)

---

## 🎯 Aperçu du Projet

Ce projet BI répond à un besoin concret des établissements d'enseignement supérieur : **prendre des décisions pédagogiques fondées sur les données**. Il met en place un Système d'Information Décisionnel (SID) complet permettant de suivre, analyser et anticiper les performances des étudiants.

### Problématiques adressées

- Quels sont les taux de réussite par filière, matière et niveau d'étude ?
- Quels facteurs socio-économiques sont corrélés au décrochage scolaire ?
- Comment évolue la moyenne d'un étudiant au fil des années ?
- Quelles matières concentrent le plus d'échecs ?

### Périmètre

| Dimension | Valeur |
|-----------|--------|
| Étudiants | 600 profils réalistes |
| Années couvertes | 2020-2021 → 2023-2024 |
| Enregistrements de notes | 7 262 |
| Cas d'abandon documentés | ~45 |
| Tables dans le modèle | 12 (2 faits + 10 dimensions) |
| Pages de dashboard | 7 |

---

## 🛠️ Stack Technique

| Composant | Technologie |
|-----------|-------------|
| Génération des données | Python / CSV |
| Base de données | MySQL 8.x |
| Pipeline ETL | Talend Open Studio 8.x |
| Visualisation | Power BI Desktop + Power BI Service |
| Modèle dimensionnel | Schéma en étoile (Ralph Kimball) |

---

## 🏗️ Architecture

```
┌─────────────────┐     ┌─────────────────────┐     ┌─────────────────────┐
│  Sources CSV    │────▶│   Talend Open Studio │────▶│   MySQL 8.x         │
│  (Python gen.)  │     │   8 jobs ETL         │     │   dwh_education      │
└─────────────────┘     └─────────────────────┘     └──────────┬──────────┘
                                                                │
                                                                ▼
                                                     ┌─────────────────────┐
                                                     │   Power BI Desktop  │
                                                     │   7 pages dashboard │
                                                     │   + Power BI Service│
                                                     └─────────────────────┘
```

L'entrepôt de données suit la **méthodologie de Ralph Kimball** (schéma en étoile), avec deux tables de faits centrales et dix dimensions.

---

## 📁 Données

### Tables de Faits

**`fait_notes`** — 7 262 lignes (fait principal)

Enregistre chaque évaluation individuelle : note sur 20, mention, statut de validation, absences, session de rattrapage.

**`fait_abandons`** — ~45 lignes (fait secondaire)

Documente les événements d'abandon scolaire avec motif (financier, personnel, académique) et semestre d'occurrence.

### Tables de Dimensions

| Table | Lignes | Description |
|-------|--------|-------------|
| `dim_etudiant` | 600 | Profil complet : démographie, niveau socio-éco, traits psychologiques, historique scolaire |
| `dim_filiere` | 10 | Formations disponibles (Licence, Master, BTS) |
| `dim_classe` | 116 | Groupes par filière et année scolaire |
| `dim_matiere` | 28 | Cours avec coefficients et crédits ECTS |
| `dim_temps` | 16 | Hiérarchie : Année → Semestre → Trimestre |
| `dim_sante_etudiant` | 600 | Santé mentale, maladies chroniques, impact sur les études |
| `dim_parents_etudiant` | 600 | Niveau d'instruction, revenus, soutien financier familial |
| `dim_integration_sociale` | 600 | Relations sociales, isolement, harcèlement |
| `dim_difficultes_academiques` | 600 | Difficultés d'adaptation, surpopulation, méthodes de travail |
| `dim_situation_economique` | 600 | Mode de vie, emploi, bourse, sources de financement |

> 📌 Les distributions statistiques (NSE : 30% Faible / 50% Moyen / 20% Élevé) sont calibrées sur la thèse de doctorat de SUEBANG Alex Roméo (2022) — *Facteurs déterminants du décrochage au premier cycle universitaire, Université de Yaoundé I*.

---

## ⚙️ Pipeline ETL

Le pipeline est orchestré par un **job maître Talend** (`Job_Master_ETL_V2`) qui enchaîne 8 jobs dans le bon ordre de dépendances :

```
Job_Master_ETL_V2
├── Job_01_Dimensions_V2     → dim_filiere, dim_temps, dim_matiere, dim_classe, dim_etudiant
├── Job_04_Sante             → dim_sante_etudiant
├── Job_05_Parents           → dim_parents_etudiant
├── Job_06_Integration       → dim_integration_sociale
├── Job_07_Difficultes       → dim_difficultes_academiques
├── Job_08_Situation_Eco     → dim_situation_economique
├── Job_02_Faits_Notes       → fait_notes (7 262 lignes)
└── Job_03_Faits_Abandons    → fait_abandons (~45 lignes)
```

Chaque job suit le pattern : `tFileInputDelimited → tMap → tMysqlOutput`

Les transformations appliquées couvrent le nettoyage des données, la normalisation, les conversions de types, les valeurs par défaut et les règles métier (calcul de mentions, statut de validation, etc.).

---

## 📊 Dashboards Power BI

Le fichier `.pbix` contient **7 pages de dashboard** publiées sur Power BI Service :

| Page | Contenu |
|------|---------|
| Vue Générale | KPIs globaux, taux de réussite et d'abandon |
| Performances par Matière | Scores moyens, taux de validation par cours |
| Analyse par Filière | Comparaison inter-filières, évolution sur 4 ans |
| Suivi Temporel | Progression des scores par trimestre et semestre |
| Profil Étudiant | Parcours individuel, progression multi-années |
| Analyse Socio-économique | Corrélations NSE / genre / performances |
| Décrochage Scolaire | Taux d'abandon, motifs, facteurs de risque |

---

## 📈 Indicateurs Clés (KPIs)

```
Taux de réussite     = (notes ≥ 10) / (total notes) × 100
Taux d'abandon       = (abandons par filière) / (effectif inscrit) × 100
Score moyen          = AVG(note) groupé par matière / classe / période
Progression individuelle = comparaison des moyennes d'une année à l'autre
```

---

## 📂 Structure du Repo

```
📦 bi-education-performance/
├── 📁 data/
│   ├── 📁 sources/          # Fichiers CSV générés (dim_*.csv, fait_*.csv)
│   └── 📁 scripts/          # Script Python de génération (generate_data.py)
├── 📁 sql/
│   └── 01_creation_schema_dwh.sql   # Création du schéma MySQL
├── 📁 etl/
│   └── 📁 talend/           # Export des jobs Talend Open Studio
├── 📁 powerbi/
│   └── education_performance.pbix   # Fichier Power BI Desktop
├── 📁 docs/
│   └── Rapport_BI_Education_Complet.docx
└── README.md
```

---

## 🚀 Installation et Configuration

### Prérequis

- MySQL 8.x
- Talend Open Studio 8.x
- Power BI Desktop

### Étapes

```bash
# 1. Cloner le repo
git clone https://github.com/<votre-username>/bi-education-performance.git
cd bi-education-performance

# 2. Créer le schéma MySQL
mysql -u root -p < sql/01_creation_schema_dwh.sql

# 3. (Optionnel) Regénérer les données sources
python data/scripts/generate_data.py

# 4. Importer et exécuter les jobs Talend
# → Ouvrir Talend Open Studio
# → Importer le projet depuis etl/talend/
# → Configurer la connexion MySQL (host, port, user, password)
# → Exécuter Job_Master_ETL_V2

# 5. Ouvrir le dashboard Power BI
# → Ouvrir powerbi/education_performance.pbix dans Power BI Desktop
# → Actualiser la source de données (connexion MySQL)
```

---

## 👤 Auteur

**Rayan NGUEBOU TEMGOUA**
Projet réalisé dans le cadre de l'année académique 2025 — 2026.

---

*Modélisation dimensionnelle basée sur la méthodologie Ralph Kimball. Données calibrées sur des références académiques du contexte universitaire africain.*
