-- add sample data to users table
insert into users(name, email, password, role) values ('Salubra', 'charm.lover@hallow.nest', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'ADMIN');
insert into users(name, email, password, role) values ('Sly', 'great.nailsage@hallow.nest', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'SELLER');
insert into users(name, email, password, role) values ('Leg Eater', 'leg.eater@hallow.nest', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'SELLER');
insert into users(name, email, password, role) values ('Iselda', 'mrs.cartographer@hallow.nest', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'SELLER');
insert into users(name, email, password, role) values ('Candelaria', 'candelaria.ladrona@miracle.com', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'SELLER');
insert into users(name, email, password, role) values ('Hornet', 'princess.knight@hallow.nest', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'USER');
insert into users(name, email, password, role) values ('Penitent One', 'mea.culpa@miracle.com', '$2a$12$e660zO9oVZzvu2vIZKUgoeydORz8Qsxz9mtU5xUHAnlngf53loAKC', 'USER');

-- add sample data to locations table 
insert into locations(region, world) values ('Forgotten Crossroads', 'Hallownest');
insert into locations(region, world) values ('Dirtmouth', 'Hallownest');
insert into locations(region, world) values ('Fungal Wastes', 'Hallownest');
insert into locations(region, world) values ('Mercy Dreams', 'Cvstodia');
insert into locations(region, world) values ('Graveyard of the Peaks', 'Cvstodia');
insert into locations(region, world) values ('The Sleeping Canvases', 'Cvstodia');

-- add sample data to charms table
insert into charms(name, description, price, location_id, seller_id) values ('Lifeblood Heart', 'When resting, the bearer will gain a coating of lifeblood that protects from a modest amount of damage.', 250, 1, 1);
insert into charms(name, description, price, location_id, seller_id) values ('Longnail', 'Increases the range of the bearer''s nail, allowing them to strike foes from further away.', 300, 1, 1);
insert into charms(name, description, price, location_id, seller_id) values ('Steady Body', 'Allows one to stay steady and keep attacking.', 120, 1, 1);
insert into charms(name, description, price, location_id, seller_id) values ('Shaman Stone', 'Increases the power of spells, dealing more damage to foes.', 220, 1, 1);
insert into charms(name, description, price, location_id, seller_id) values ('Quick Focus', 'Increases the speed of focusing soul, allowing the bearer to heal damage faster.', 800, 1, 1);
insert into charms(name, description, price, location_id, seller_id) values ('Gathering Swarm', 'Useful for those who can''t bear to leave anything behind, no matter how insignificant.', 300, 2, 2);
insert into charms(name, description, price, location_id, seller_id) values ('Stalwart Shell', 'Makes it easier to escape from dangerous situations.', 200, 2, 2);
insert into charms(name, description, price, location_id, seller_id) values ('Heavy Blow', 'Increases the force of the bearer''s nail, causing enemies to recoil further when hit.', 350, 2, 2);
insert into charms(name, description, price, location_id, seller_id) values ('Sprintmaster', 'Increases the running speed of the bearer, allowing them to avoid danger or overtake rivals.', 400, 2, 2);
insert into charms(name, description, price, location_id, seller_id) values ('Fragile Heart', 'Increases the health of the bearer, allowing them to take more damage.', 350, 3, 3);
insert into charms(name, description, price, location_id, seller_id) values ('Fragile Greed', 'Causes the bearer to find more Geo when defeating enemies.', 250, 3, 3);
insert into charms(name, description, price, location_id, seller_id) values ('Fragile Strength', 'Strengthens the bearer, increasing the damage they deal to enemies with their nail.', 600, 3, 3);
insert into charms(name, description, price, location_id, seller_id) values ('Wayward Compass', 'Whispers its location to the bearer whenever a map is open, allowing wanderers to pinpoint their current location.', 220, 2, 4);
insert into charms(name, description, price, location_id, seller_id) values ('Hollow Pearl', 'Empty, tarnished sphere. There is no trace of nacre left, but it retains just a hint of bluish sheen. It creates fervour when destroying surrounding objects.', 100, 4, 5);
insert into charms(name, description, price, location_id, seller_id) values ('Moss Preserved in Glass', 'Minute jar filled with moss. The tiny white flowers that shimmer inside can grant some protection against toxins.', 1500, 4, 5);
insert into charms(name, description, price, location_id, seller_id) values ('Ember of the Holy Cremation', 'Ember crystallised through a long forgotten ritual. It retains some of the heat of a bonfire lit on a different land. Tempers the heart, increasing the defences of whoever carries it. ', 5000, 5, 5);
insert into charms(name, description, price, location_id, seller_id) values ('Calcified Eye of Erudition', 'Eyeball belonging to Alavaroz the scribe, who glimpsed the truth hidden to so many other eyes. Its stony scrutiny reveals the vital essence of enemies. ', 1200, 5, 5);
insert into charms(name, description, price, location_id, seller_id) values ('Wicker Knot', 'Bead braided in a spiral motion, covered with a light coat of varnish that has protected it from the wear and tear of the years. A mother''s hands worked these strands and blessed them. Their influence provides protection from toxic damage.', 4000, 6, 5);
