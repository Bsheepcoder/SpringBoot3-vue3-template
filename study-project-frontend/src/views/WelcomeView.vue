<script setup>

</script>

<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1;background-color: dodgerblue">
            <el-image style="width: 100%;height: 100%;"  fit="cover"
                      src="https://ts1.cn.mm.bing.net/th/id/R-C.765c10656bdb92ea1af463233beea47e?rik=q2%2buru5ljnLwkA&riu=http%3a%2f%2f4k.znds.com%2f20140314%2f4kznds3.jpg&ehk=A%2fjdaY7%2b9QMUdN4jcAMvwnnwDJnH0%2fkg1U5g4cDee4c%3d&risl=&pid=ImgRaw&r=0"/>
        </div>
        <div class="welcome-title" >
            <div style="font-size: 30px;font-weight: bold">欢迎进入达梦大数据物流中台系统</div>
            <div style="margin-top: 10px">结合大数据平台，为客户提供数据获取、数据治理、数据共享等优质服务！</div>
        </div>
        <div style="width: 400px;background-color: white;z-index: 1">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear">
                    <component :is="Component" />
                </transition>
            </router-view>
        </div>
    </div>
</template>

<style scoped>
.welcome-title{
    position: absolute;
    bottom:30px;
    left: 30px;
    color:white;
    text-shadow: 0 0 10px black;
}
</style>