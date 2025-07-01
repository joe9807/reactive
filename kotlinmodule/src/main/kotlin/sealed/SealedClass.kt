package sealed

sealed class SealedClass {
    var type: Int = 0
    var value: String = "";

    object Success: SealedClassSuccess()
    object Error: SealedClassError()
    object Loading: SealedClassLoading();
}

open class SealedClassLoading: SealedClass() {

}

open class SealedClassError: SealedClass() {

}

open class SealedClassSuccess: SealedClass() {

}

open class SealedClassSuccess11: SealedClassSuccess(){

}

open class SealedClassSuccess112{

}