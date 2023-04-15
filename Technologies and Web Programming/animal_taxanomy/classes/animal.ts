abstract class Animal {
    static nAnimal = 0;
    habitat: string;

    protected constructor(habitat: string) {
        this.habitat = habitat;
        Animal.nAnimal++;
    }

    abstract show(): void;
}
 export { Animal };