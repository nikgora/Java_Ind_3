public class People implements Comparable<People>{
    private String name;
    private String secondName;
    private int age;
    private String sex;

    public People(String name, String secondName, int age, String sex) {
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public int compareTo(People other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int secondNameComparison = this.secondName.compareTo(other.secondName);
        if (secondNameComparison != 0) {
            return secondNameComparison;
        }

        if (this.age < other.age) {
            return -1;
        } else if (this.age > other.age) {
            return 1;
        }

        return this.sex.compareTo(other.sex);
    }
}
