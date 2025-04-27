-- Création de l'utilisateur s'il n'existe pas déjà
CREATE USER IF NOT EXISTS 'userEPF'@'%' IDENTIFIED BY 'EPF';

-- Attribution de tous les privilèges à l'utilisateur sur la base de données pvz
GRANT ALL PRIVILEGES ON pvz.* TO 'userEPF'@'%';

-- Application des changements de privilèges
FLUSH PRIVILEGES;
```