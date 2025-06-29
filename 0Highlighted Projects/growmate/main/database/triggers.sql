CREATE OR REPLACE FUNCTION add_plant_difficulty()
RETURNS TRIGGER AS $$
DECLARE
  plant_difficulty INTEGER;
BEGIN

  SELECT public.species_family.difficulty
  INTO plant_difficulty
  FROM public.species_family
  WHERE public.species_family.id = NEW.family_id;

  NEW.difficulty = ROUND((
      (ABS(sin((pi()/2)*NEW.optimal_temperature))*5.0)* 0.1 +
      (5.0/3.0) * (NEW.optimal_luminosity-1) * 0.2 + 
      (5.0/2.0) * (NEW.optimal_humidity-1) * 0.2 + 
      (5.0/2.0) * (NEW.watering_frequency-1) * 0.35 + 
      (plant_difficulty-1) * 0.15
    )+0.5);

    RAISE NOTICE 'Temp: %', (ABS(sin((pi()/2)*NEW.optimal_temperature))*5.0);
    RAISE NOTICE 'Lum: %', (4.0/3.0) * (NEW.optimal_luminosity-1);
    RAISE NOTICE 'Hum: %', (4.0/2.0) * (NEW.optimal_humidity-1);
    RAISE NOTICE 'Wfreq: %', (4.0/2.0) * (NEW.watering_frequency-1);
    RAISE NOTICE 'FamDiff: %', plant_difficulty;
    RAISE NOTICE 'Exact value: %', ((ABS(sin((pi()/2)*NEW.optimal_temperature))*5.0)* 0.1 +
      (4.0/3.0) * (NEW.optimal_luminosity-1) * 0.2 + 
      (4.0/2.0) * (NEW.optimal_humidity-1) * 0.2 + 
      (4.0/2.0) * (NEW.watering_frequency-1) * 0.35 + 
      (plant_difficulty-1) * 0.15) +0.5;
    RAISE NOTICE 'Result: %', NEW.difficulty;


  RETURN NEW;
  
END
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_plant_difficulty_trigger
BEFORE INSERT OR UPDATE ON public.plant_species
FOR EACH ROW
EXECUTE PROCEDURE add_plant_difficulty();