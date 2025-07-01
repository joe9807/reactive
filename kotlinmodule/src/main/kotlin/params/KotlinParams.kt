package params

fun main(){
    val user1 = createUser(age = 10, name = "name1", position = "position1", salary = 20000, gender = GenderEnum.MALE)
    print1(user1)

    val user2 = createUser(name = "name1", position = "position1", salary = 200000, gender = GenderEnum.FEMALE, age = 10)
    print1(user2)
}

fun createUser(name: String, age: Int, admin: Boolean = false, salary: Int = 0, position: String, gender: GenderEnum): User{
    return User(name, age, admin, salary, position, gender)
}

data class User(val name: String, val age: Int, val admin: Boolean = false, val salary: Int = 0, val position: String, val male: GenderEnum)

fun print1(user: User){
    println(user.toString())
}