import {Mammal} from "./mammal";

class Canine extends Mammal {
    static nCanines = 0;
    race: string;
    constructor(habitat: string, race:string) {
        super(habitat);
        this.race = race;
        Canine.nCanines++;
    }
} export { Canine };