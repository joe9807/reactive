package sealed

fun main(){
    println(KotlinVersion.CURRENT)
    SealedClass.Success.value = "first";
    SealedClass.Success.type = 6;
    KotlinExample().sealedWhenType(SealedClass.Success)
    KotlinExample().sealedWhenClass(SealedClass.Success)
    SealedClass.Error.type = 6;
    SealedClass.Error.value = "second";
    KotlinExample().sealedWhenType(SealedClass.Error)
    KotlinExample().sealedWhenClass(SealedClass.Error)
    SealedClass.Loading.type = 3;
    SealedClass.Loading.value = "third";
    KotlinExample().sealedWhenType(SealedClass.Loading)
    KotlinExample().sealedWhenClass(SealedClass.Loading)
}

class KotlinExample {
    fun sealedWhenType(sealedClass: SealedClass){
        when(sealedClass.type){
            0 -> println("success ${sealedClass.value}")
            1 -> println("error ${sealedClass.value}")
            else -> {println("loading ${sealedClass.value}")}
        }
    }

    fun sealedWhenClass(sealedClass: SealedClass){
        when(sealedClass){
            is SealedClassSuccess -> println("success")
            is SealedClassError -> println("error")
            is SealedClassLoading -> println("loading")
        }
    }
}