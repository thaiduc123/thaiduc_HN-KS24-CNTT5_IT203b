class User {
    int age;
    void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Tuổi không âm");
        }
        this.age = age;
    }
    public int getAge() {
        return age;
    }
}