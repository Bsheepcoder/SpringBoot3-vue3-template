
<template>
    <transition  name="el-fade-in-linear" mode="out-in">
        <div style="padding: 20px;height: 100%" v-if="active === 0">
            <div style="margin-top: 130px;text-align: center">
                <div style="font-size: 25px">重置密码</div>
                <div style="font-size: 14px">请输入账户的电子邮件地址</div>
            </div>
            <div style="padding: 10px;margin-top: 20px;" >
                <el-steps :active="active" finish-status="success" style="font-size: 14px" align-center>
                    <el-step title="验证邮箱"></el-step>
                    <el-step title="重设密码"></el-step>
                </el-steps>
            </div>
            <div style="margin-top: 30px">
                <el-form  :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                    <el-form-item prop="email">
                        <el-input v-model="form.email"  type="email" placeholder="邮箱">
                            <template #prefix>
                                <el-icon><Message /></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="code">
                        <el-row :gutter="10" style="display:flow; width:100%;">
                            <el-col :span="17" >
                                <el-input v-model="form.code" :maxLength="6" type="text" :maxlength="6" placeholder="验证码">
                                    <template #prefix>
                                        <el-icon><EditPen /></el-icon>
                                    </template>
                                </el-input>
                            </el-col>
                            <el-col :span="6">
                                <el-button type="success" @click="validateEmail"
                                           :disabled="!isEmailValid|| coldTime>0">
                                    {{ coldTime > 0 ? '请稍后'+coldTime+'秒':'获取验证码' }}
                                </el-button>
                            </el-col>
                        </el-row>
                    </el-form-item>
                </el-form>
            </div>
            <div style="margin-top: 40px;text-align: center">
                <el-button @click="startReset" style="width: 270px;" type="warning">开始重置密码</el-button>
            </div>
        </div>

    </transition>
    <transition  name="el-fade-in-linear" mode="out-in">
    <div style="padding: 20px" v-if="active === 1">
        <div style="margin-top: 130px;text-align: center">
            <div style="font-size: 25px">重置密码</div>
            <div style="font-size: 14px">请重复输入新密码</div>
        </div>
        <div style="padding: 10px;margin-top: 20px;" >
            <el-steps :active="active" finish-status="success" style="font-size: 14px" align-center>
                <el-step title="验证邮箱"></el-step>
                <el-step title="重设密码"></el-step>
            </el-steps>
        </div>
        <div style="margin-top: 50px">
            <el-form  :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                <el-form-item prop="password">
                    <el-input  v-model="form.password" :maxlength="16" type="password" placeholder="密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password_repeat">
                    <el-input  v-model="form.password_repeat" :maxLength="16" type="password" placeholder="重复输入密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
            </el-form>
        </div>
        <div style="margin-top: 40px;text-align: center">
            <el-button @click="doReset" style="width: 270px;" type="warning">立即重置密码</el-button>
        </div>
    </div>
    </transition>
</template>

<style scoped>

</style>

<script setup>

import router from "@/router";
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";

const formRef = ref() //获取表单信息
const isEmailValid = ref(false)
const active = ref(0);
const coldTime = ref(0)
const form = reactive(
    {
        username:'',
        password:'',
        password_repeat:'',
        email:'',
        code:''
    }
)

const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
}

const rules = {
    password:[
        {required: true,message: '请输入密码',tigger: 'blur'},
        { min: 6, max: 16, message: '密码长度必须在6-16个字符之间', trigger: ['blur','change'] },
    ],
    password_repeat:[
        { validator:validatePassword, trigger: ['blur','change'] },
    ],
    email:[
        {required: true,message: '请输入邮件地址',tigger: 'blur'},
        {type:'email',message: '请输入合法的邮件地址',trigger: ['blur', 'change'],}
    ],
    code:[
        {required: true,message: '请输入获取的验证码',tigger: 'blur'},
    ]
}
const onValidate = (prop,isValid)=>{
    if(prop==='email')
        isEmailValid.value = isValid
}
const validateEmail = () =>{
    post('/api/auth/valid-reset-email',{
        email:form.email
    },(message)=>{
        ElMessage.success(message)
        coldTime.value = 60
        setInterval(()=>coldTime.value--,1000)
    } )
}

const startReset = () =>{
    formRef.value.validate((isValid)=>{
        if(isValid){
            post('/api/auth/start-rest',{
                email: form.email,
                code: form.code
            }, () =>{
                active.value = 1
            })
        }else{
            ElMessage.warning('请填写电子邮件地址和验证码')
        }
    })

}

const doReset = () =>{
    formRef.value.validate((isValid) => {
        if(isValid){
            post('/api/auth/do-rest',{
                password:form.password
            }, () =>{
                ElMessage.success('密码重置成功！')
                router.push('/')
            })
        }else{
            ElMessage.warning('请填写新密码')
        }
    })
}



</script>
