import pandas as pd
import numpy as np
import random
import os
from datetime import date, timedelta

random.seed(42)
np.random.seed(42)

OUT = "/home/claude/education_bi_data"
os.makedirs(OUT, exist_ok=True)

# ─────────────────────────────────────────────
# 1. DIM_FILIERE
# ─────────────────────────────────────────────
filieres = [
    ("FIL001", "Informatique",         "Sciences & Technologie", 3, "Licence"),
    ("FIL002", "Mathématiques",         "Sciences Fondamentales", 3, "Licence"),
    ("FIL003", "Économie-Gestion",      "Sciences Sociales",      3, "Licence"),
    ("FIL004", "Lettres Modernes",      "Sciences Humaines",      3, "Licence"),
    ("FIL005", "Sciences Biologiques",  "Sciences de la Vie",     3, "Licence"),
    ("FIL006", "Droit",                 "Sciences Juridiques",    3, "Licence"),
    ("FIL007", "Génie Civil",           "Sciences & Technologie", 5, "Master"),
    ("FIL008", "Informatique",          "Sciences & Technologie", 2, "Master"),
    ("FIL009", "Management",            "Sciences Sociales",      2, "Master"),
    ("FIL010", "Comptabilité-Finance",  "Sciences Sociales",      2, "BTS"),
]
df_filiere = pd.DataFrame(filieres, columns=[
    "code_filiere","nom_filiere","domaine","duree_annees","niveau_diplome"
])
df_filiere.insert(0, "id_filiere", range(1, len(filieres)+1))

# ─────────────────────────────────────────────
# 2. DIM_TEMPS  (annees scolaires 2020-21 → 2023-24)
# ─────────────────────────────────────────────
temps_rows = []
tid = 1
for annee_debut in [2020, 2021, 2022, 2023]:
    annee_sco = f"{annee_debut}-{str(annee_debut+1)[-2:]}"
    for sem in [1, 2]:
        for tri in [1, 2] if sem == 1 else [3, 4]:
            mois_debut = {1:9, 2:11, 3:2, 4:4}[tri]
            temps_rows.append({
                "id_temps": tid,
                "annee_scolaire": annee_sco,
                "annee": annee_debut if sem == 1 else annee_debut + 1,
                "semestre": sem,
                "trimestre": tri,
                "periode": f"S{sem}T{tri % 2 or 2}",
                "mois_debut": mois_debut
            })
            tid += 1
df_temps = pd.DataFrame(temps_rows)

# ─────────────────────────────────────────────
# 3. DIM_CLASSE
# ─────────────────────────────────────────────
niveaux_par_filiere = {
    1: ["L1","L2","L3"],
    2: ["L1","L2","L3"],
    3: ["L1","L2","L3"],
    4: ["L1","L2","L3"],
    5: ["L1","L2","L3"],
    6: ["L1","L2","L3"],
    7: ["G1","G2","G3","G4","G5"],
    8: ["M1","M2"],
    9: ["M1","M2"],
    10: ["BTS1","BTS2"],
}
classe_rows = []
cid = 1
for annee_debut in [2020, 2021, 2022, 2023]:
    annee_sco = f"{annee_debut}-{str(annee_debut+1)[-2:]}"
    for fid, niveaux in niveaux_par_filiere.items():
        for niv in niveaux:
            fil_code = df_filiere.loc[df_filiere['id_filiere']==fid, 'code_filiere'].values[0]
            effectif = random.randint(25, 65)
            classe_rows.append({
                "id_classe": cid,
                "code_classe": f"{fil_code[-3:]}-{niv}-{annee_debut}",
                "id_filiere": fid,
                "niveau_annee": niv,
                "annee_scolaire": annee_sco,
                "effectif_inscrit": effectif,
                "salle": f"Bloc {random.choice('ABCD')}-{random.randint(1,10):02d}"
            })
            cid += 1
df_classe = pd.DataFrame(classe_rows)

# ─────────────────────────────────────────────
# 4. DIM_MATIERE
# ─────────────────────────────────────────────
matieres_def = [
    # (code, nom, id_filiere, coeff, credits, type, semestre)
    ("M001","Algorithmique",          1,3,6,"Fondamentale",1),
    ("M002","Programmation Python",   1,3,5,"Fondamentale",1),
    ("M003","Base de données",        1,4,6,"Fondamentale",2),
    ("M004","Réseaux informatiques",  1,3,5,"Fondamentale",2),
    ("M005","Mathématiques Discrètes",1,2,4,"Complémentaire",1),
    ("M006","Systèmes d'exploitation",1,3,5,"Fondamentale",2),
    ("M007","Analyse Mathématique",   2,4,7,"Fondamentale",1),
    ("M008","Algèbre Linéaire",       2,4,6,"Fondamentale",1),
    ("M009","Probabilités-Statistiques",2,3,6,"Fondamentale",2),
    ("M010","Topologie",              2,3,5,"Fondamentale",2),
    ("M011","Microéconomie",          3,3,5,"Fondamentale",1),
    ("M012","Macroéconomie",          3,3,5,"Fondamentale",2),
    ("M013","Comptabilité Générale",  3,3,6,"Fondamentale",1),
    ("M014","Droit des Affaires",     3,2,4,"Complémentaire",2),
    ("M015","Littérature Française",  4,3,5,"Fondamentale",1),
    ("M016","Linguistique",           4,3,5,"Fondamentale",2),
    ("M017","Biologie Cellulaire",    5,4,6,"Fondamentale",1),
    ("M018","Chimie Organique",       5,3,5,"Fondamentale",1),
    ("M019","Droit Constitutionnel",  6,3,6,"Fondamentale",1),
    ("M020","Droit Civil",            6,4,6,"Fondamentale",2),
    ("M021","Résistance des Matériaux",7,4,7,"Fondamentale",1),
    ("M022","Mécanique des Fluides",  7,3,6,"Fondamentale",2),
    ("M023","Machine Learning",       8,4,7,"Fondamentale",1),
    ("M024","Big Data & Cloud",       8,3,6,"Fondamentale",2),
    ("M025","Stratégie d'Entreprise", 9,3,6,"Fondamentale",1),
    ("M026","Finance d'Entreprise",   10,3,6,"Fondamentale",1),
    ("M027","Anglais Technique",      1,2,3,"Transversale",1),
    ("M028","Communication Prof.",    3,2,3,"Transversale",2),
]
df_matiere = pd.DataFrame(matieres_def, columns=[
    "code_matiere","nom_matiere","id_filiere","coefficient","credits","type_matiere","semestre"
])
df_matiere.insert(0,"id_matiere", range(1, len(matieres_def)+1))

# ─────────────────────────────────────────────
# 5. DIM_ETUDIANT (600 étudiants)
# ─────────────────────────────────────────────
prenoms_h = ["Alain","Bruno","Carlos","David","Emmanuel","François","Georges","Hugo",
             "Ibrahim","Jean","Kevin","Louis","Marc","Nicolas","Olivier","Patrick",
             "Quentin","Rodrigue","Samuel","Thomas","Ulrich","Vincent","William","Xavier",
             "Yann","Zacharie","André","Bertrand","Christophe","Didier","Eric","Fabrice",
             "Gabriel","Henri","Isidore","Jacques","Laurent","Michel","Nathan","Pascal"]
prenoms_f = ["Alice","Béatrice","Carole","Diane","Élise","Fatima","Grace","Hortense",
             "Isabelle","Julie","Karine","Laura","Marie","Nathalie","Océane","Patricia",
             "Reine","Sophie","Thérèse","Ursula","Valérie","Wanda","Xochitl","Yvonne",
             "Zahra","Amina","Blandine","Cécile","Danielle","Esther","Françoise","Gisèle",
             "Honorine","Irène","Joëlle","Laure","Marguerite","Noëlle","Pauline","Rosalie"]
noms = ["Atangana","Bello","Camara","Diallo","Ebene","Foko","Gomis","Haidara",
        "Issa","Jourdain","Kamga","Lobe","Manga","Nguema","Okafor","Penda",
        "Rodrigues","Samba","Toure","Umba","Vidal","Wamba","Yanga","Zobel",
        "Martin","Dupont","Bernard","Thomas","Petit","Robert","Richard","Simon",
        "Michel","Lefebvre","Leroy","Moreau","Simon","Bouchard","Dubois","Garcia"]
nse_options = ["Faible","Moyen","Élevé"]
nse_weights = [0.30, 0.50, 0.20]

etudiant_rows = []
eid = 1
annee_entrees = [2020, 2021, 2022, 2023]
for _ in range(600):
    genre = random.choice(["M","F"])
    prenom = random.choice(prenoms_h if genre=="M" else prenoms_f)
    nom = random.choice(noms)
    annee_naissance = random.randint(1998, 2004)
    mois = random.randint(1, 12)
    jour = random.randint(1, 28)
    nse = random.choices(nse_options, weights=nse_weights)[0]
    filiere_id = random.randint(1, 10)
    annee_entree = random.choice(annee_entrees)
    etudiant_rows.append({
        "id_etudiant": eid,
        "matricule": f"ETU{eid:04d}",
        "nom": nom,
        "prenom": prenom,
        "genre": genre,
        "date_naissance": f"{annee_naissance}-{mois:02d}-{jour:02d}",
        "niveau_socio_economique": nse,
        "id_filiere": filiere_id,
        "annee_entree": annee_entree,
        "ville_origine": random.choice(["Yaoundé","Douala","Bafoussam","Garoua","Maroua","Bamenda","Ngaoundéré","Bertoua"])
    })
    eid += 1
df_etudiant = pd.DataFrame(etudiant_rows)

# ─────────────────────────────────────────────
# 6. FAIT_NOTES  (table de faits principale)
# ─────────────────────────────────────────────
def get_mention(note):
    if note >= 16: return "Très Bien"
    if note >= 14: return "Bien"
    if note >= 12: return "Assez Bien"
    if note >= 10: return "Passable"
    return "Insuffisant"

def get_statut(note):
    if note >= 10: return "Validé"
    return "Non Validé"

# Paramètres de performance par NSE
perf_params = {
    "Faible":  (9.5,  3.5),
    "Moyen":   (11.5, 3.0),
    "Élevé":   (13.5, 2.5),
}

note_rows = []
nid = 1

# Pour chaque étudiant, on génère ses notes sur les années scolaires
for _, etu in df_etudiant.iterrows():
    fid = etu["id_filiere"]
    nse = etu["niveau_socio_economique"]
    annee_entree = etu["annee_entree"]
    mu, sigma = perf_params[nse]

    # Légère variation individuelle
    mu_indi = mu + random.gauss(0, 1.0)
    mu_indi = max(4, min(18, mu_indi))

    # Matières de sa filière
    mats = df_matiere[df_matiere["id_filiere"] == fid]
    if len(mats) == 0:
        continue

    # Années scolaires disponibles
    annees_sco = [f"{a}-{str(a+1)[-2:]}" for a in range(annee_entree, min(annee_entree+3, 2024))]

    # Probabilité d'abandon (plus élevée pour NSE Faible)
    p_abandon = {"Faible": 0.18, "Moyen": 0.08, "Élevé": 0.03}[nse]
    abandon = random.random() < p_abandon

    for i_annee, annee_sco in enumerate(annees_sco):
        if abandon and i_annee == len(annees_sco)-1:
            # Étudiant abandonne en cours d'année (génère quelques notes puis s'arrête)
            mats_subset = mats.sample(min(3, len(mats)))
        else:
            mats_subset = mats

        # Trouver les temps correspondants
        temps_annee = df_temps[df_temps["annee_scolaire"] == annee_sco]
        # Trouver la classe correspondante
        classes_annee = df_classe[
            (df_classe["id_filiere"] == fid) &
            (df_classe["annee_scolaire"] == annee_sco)
        ]
        if len(classes_annee) == 0:
            continue
        classe = classes_annee.sample(1).iloc[0]

        for _, mat in mats_subset.iterrows():
            # Choisir les trimestres correspondant au semestre de la matière
            sem_mat = mat["semestre"]
            temps_sem = temps_annee[temps_annee["semestre"] == sem_mat]
            if len(temps_sem) == 0:
                temps_sem = temps_annee

            for _, temps in temps_sem.iterrows():
                # Note avec légère tendance à progresser dans le temps
                progression = i_annee * random.uniform(0.1, 0.4)
                note_raw = np.random.normal(mu_indi + progression, sigma)
                note = round(max(0, min(20, note_raw)), 2)
                absences = max(0, int(np.random.poisson(2.5 - (mu_indi / 8))))

                note_rows.append({
                    "id_note": nid,
                    "id_etudiant": int(etu["id_etudiant"]),
                    "id_matiere": int(mat["id_matiere"]),
                    "id_classe": int(classe["id_classe"]),
                    "id_temps": int(temps["id_temps"]),
                    "note": note,
                    "mention": get_mention(note),
                    "statut_validation": get_statut(note),
                    "nb_absences": absences,
                    "annee_scolaire": annee_sco,
                    "est_rattrapage": int(note < 8 and random.random() < 0.4)
                })
                nid += 1
df_notes = pd.DataFrame(note_rows)

# ─────────────────────────────────────────────
# 7. FAIT_ABANDONS
# ─────────────────────────────────────────────
abandon_rows = []
aid = 1
motifs = ["Difficultés financières","Raisons familiales","Manque de motivation",
          "Difficultés académiques","Réorientation","Problème de santé","Autre"]
motif_weights = [0.30, 0.20, 0.15, 0.20, 0.08, 0.04, 0.03]

for _, etu in df_etudiant.iterrows():
    nse = etu["niveau_socio_economique"]
    p_ab = {"Faible": 0.18, "Moyen": 0.08, "Élevé": 0.03}[nse]
    if random.random() < p_ab:
        annee_ab = etu["annee_entree"] + random.randint(0, 2)
        if annee_ab <= 2023:
            annee_sco = f"{annee_ab}-{str(annee_ab+1)[-2:]}"
            motif = random.choices(motifs, weights=motif_weights)[0]
            abandon_rows.append({
                "id_abandon": aid,
                "id_etudiant": int(etu["id_etudiant"]),
                "id_filiere": int(etu["id_filiere"]),
                "annee_scolaire": annee_sco,
                "annee_abandon": annee_ab,
                "motif_abandon": motif,
                "semestre_abandon": random.choice([1, 2])
            })
            aid += 1
df_abandons = pd.DataFrame(abandon_rows)

# ─────────────────────────────────────────────
# 8. EXPORT CSV
# ─────────────────────────────────────────────
tables = {
    "dim_filiere":   df_filiere,
    "dim_temps":     df_temps,
    "dim_classe":    df_classe,
    "dim_matiere":   df_matiere,
    "dim_etudiant":  df_etudiant,
    "fait_notes":    df_notes,
    "fait_abandons": df_abandons,
}

for name, df in tables.items():
    path = f"{OUT}/{name}.csv"
    df.to_csv(path, index=False, encoding="utf-8-sig")
    print(f"  ✓  {name:<20} {len(df):>6} lignes  →  {path.split('/')[-1]}")

print(f"\n  Total notes générées  : {len(df_notes):,}")
print(f"  Total étudiants       : {len(df_etudiant):,}")
print(f"  Total abandons        : {len(df_abandons):,}")
print(f"  Période couverte      : 2020-21 → 2023-24")
