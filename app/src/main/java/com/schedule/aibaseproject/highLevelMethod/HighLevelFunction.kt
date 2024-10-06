package com.schedule.aibaseproject.highLevelMethod

class HighLevelFunction {
    private constructor() {
    }

    fun sendToFunction(sum:Int,funType:(Int,Int)->Int):Boolean{
        if (sum == funType(2,3)){
            return true
        }
        return false
    }

    fun sendToHighFunction(sum: Int, funType: (a:Int, b:Int,c:(Int,Int)->Int)->Int):Boolean{
        if (sum == funType(2,3, ::add)){
            return true
        }
        return false
    }

    private fun add(a:Int,b:Int):Int{
        return a+b
    }

    companion object{
        private var instance: HighLevelFunction? = null
        fun getInstance():HighLevelFunction?{
            if(instance == null){
                instance = HighLevelFunction()
            }
            return instance
        }
    }
}