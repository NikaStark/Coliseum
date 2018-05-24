INSERT INTO halls VALUES
  (1, 'Black', 15, 22),
  (2, 'Blue', 10, 16),
  (3, 'Pink', 12, 18),
  (4, 'Yellow', 10, 16),
  (5, 'Green', 10, 16);

INSERT INTO movies VALUES
  ('6d864d62-5eff-11e8-9c2d-fa7ae01bbebc', 'Lost in space', 107, 'SOON'),
  ('6d86505a-5eff-11e8-9c2d-fa7ae01bbebc', 'It', 110, 'IN'),
  ('6d865320-5eff-11e8-9c2d-fa7ae01bbebc', 'Dunkirk', 91, 'IN'),
  ('6d8654b0-5eff-11e8-9c2d-fa7ae01bbebc', 'Memento', 122, 'OUT'),
  ('6d86571c-5eff-11e8-9c2d-fa7ae01bbebc', 'Aliens', 134, 'OUT');

INSERT INTO users VALUES
  ('db81c084-80f9-11e6-ae22-56b6b6499611', '99999', 'qwerty', '@mail', 'Alvian', 'Fedoceev', 'CLIENT'),
  ('db81c354-80f9-11e6-ae22-56b6b6499611', '19875', 'qazwsx', '@gmail', 'Eduard', 'Guseev', 'CLIENT'),
  ('db81c502-80f9-11e6-ae22-56b6b6499611', '19889', 'zimeeko', '@yahoo', 'Tichon', 'Egorov', 'CLIENT'),
  ('db81c67e-80f9-11e6-ae22-56b6b6499611', '19801', 'tribel', '@yandex', 'Avkensiy', 'Subotin', 'MANAGER'),
  ('db81c818-80f9-11e6-ae22-56b6b6499611', '19900', 'isaiev', '@ukr', 'Efim', 'Alkseev', 'MANAGER');

INSERT INTO sessions VALUES
  ('8354bcae-5f00-11e8-9c2d-fa7ae01bbebc', '6d86505a-5eff-11e8-9c2d-fa7ae01bbebc',
   2, '2018-06-01 10:10:25', 10, 'EXPECTED'),

  ('8354bf2e-5f00-11e8-9c2d-fa7ae01bbebc', '6d86505a-5eff-11e8-9c2d-fa7ae01bbebc',
   2, '2018-06-01 12:15:59', 10, 'EXPECTED'),

  ('8354c12c-5f00-11e8-9c2d-fa7ae01bbebc', '6d86505a-5eff-11e8-9c2d-fa7ae01bbebc',
   4, '2018-06-01 14:20:33', 10, 'EXPECTED'),

  ('8354c29e-5f00-11e8-9c2d-fa7ae01bbebc', '6d865320-5eff-11e8-9c2d-fa7ae01bbebc',
   1, '2018-06-01 16:01:46', 15, 'EXPECTED'),

  ('8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', '6d8654b0-5eff-11e8-9c2d-fa7ae01bbebc',
   1, '2018-06-01 18:32:00', 15, 'EXPECTED');

INSERT INTO tickets VALUES
  ('a4b771e2-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c084-80f9-11e6-ae22-56b6b6499611', '8354bcae-5f00-11e8-9c2d-fa7ae01bbebc', 9, 12, 'PURCHASED'),
  ('a4b77584-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c084-80f9-11e6-ae22-56b6b6499611', '8354bf2e-5f00-11e8-9c2d-fa7ae01bbebc', 3, 12, 'PURCHASED'),
  ('a4b77818-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c084-80f9-11e6-ae22-56b6b6499611', '8354bf2e-5f00-11e8-9c2d-fa7ae01bbebc', 1, 1, 'PURCHASED'),
  ('a4b77dc2-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c354-80f9-11e6-ae22-56b6b6499611', '8354c12c-5f00-11e8-9c2d-fa7ae01bbebc', 7, 1, 'PURCHASED'),
  ('a4b78042-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c354-80f9-11e6-ae22-56b6b6499611', '8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', 6, 5, 'PURCHASED'),
  ('a4b78394-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c354-80f9-11e6-ae22-56b6b6499611', '8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', 5, 10, 'PURCHASED'),
  ('a4b78614-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c354-80f9-11e6-ae22-56b6b6499611', '8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', 7, 4, 'PURCHASED'),
  ('a4b78876-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c354-80f9-11e6-ae22-56b6b6499611', '8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', 7, 5,  'PURCHASED'),
  ('a4b78ac4-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c354-80f9-11e6-ae22-56b6b6499611', '8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', 7, 6, 'PURCHASED'),
  ('a4b7901e-5f01-11e8-9c2d-fa7ae01bbebc', 'db81c502-80f9-11e6-ae22-56b6b6499611', '8354c4f6-5f00-11e8-9c2d-fa7ae01bbebc', 7, 7, 'PURCHASED');