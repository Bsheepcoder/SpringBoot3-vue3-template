

<template>
  <div class="common-layout">
    <el-container style="height: auto">
        <el-header style="padding: 0">
            <el-menu
                    :default-active="activeIndex"
                    class="el-menu-demo"
                    mode="horizontal"
                    :ellipsis="false"
                    @select="handleSelect"
                    background-color="#ffffff"

            >
                <el-menu-item index="0" style="color: black;font-size: 26px" >物流中台系统</el-menu-item>
                <div class="flex-grow" />
                    <el-sub-menu index="2">
                        <template #title>账户</template>
                        <el-menu-item index="2-1">个人信息</el-menu-item>
                        <el-menu-item index="2-2">其他</el-menu-item>
                        <el-menu-item index="2-3" @click="logout">退出登录</el-menu-item>
                    </el-sub-menu>
            </el-menu>
            </el-header>
        <el-container>
            <el-aside width="200px">
                        <el-menu
                                default-active="2"
                                class="el-menu-vertical-demo"
                        >
                            <el-sub-menu index="1">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>数据分析展示</span>
                                </template>
                                <el-menu-item-group title="Group One">
                                    <el-menu-item index="1-1">item one</el-menu-item>
                                    <el-menu-item index="1-2">item two</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group title="Group Two">
                                    <el-menu-item index="1-3">item three</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="2">
                                <template #title>
                                <el-icon><icon-menu /></el-icon>
                                <span>数据采集</span>
                                </template>

                                <el-menu-item index="2-1"  @click="router.push('index/fileimport')">文件导入</el-menu-item>
                                <el-menu-item index="2-2"  @click="router.push('index/dbmove')" >数据库转存</el-menu-item>
                                <el-menu-item index="2-3">other</el-menu-item>
                            </el-sub-menu>
                            <el-sub-menu index="3">
                                <template #title>
                                    <el-icon><icon-menu /></el-icon>
                                    <span>数据治理</span>
                                </template>
                                <el-menu-item-group title="Group One">
                                    <el-menu-item index="1-1">item one</el-menu-item>
                                    <el-menu-item index="1-2">item two</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group title="Group Two">
                                    <el-menu-item index="1-3">item three</el-menu-item>
                                </el-menu-item-group>
                                <el-sub-menu index="1-4">
                                    <template #title>item four</template>
                                    <el-menu-item index="1-4-1">item one</el-menu-item>
                                </el-sub-menu>
                            </el-sub-menu>
                            <el-sub-menu index="4">
                                <template #title>
                                    <el-icon><icon-menu /></el-icon>
                                    <span>数据服务</span>
                                </template>
                                <el-menu-item-group title="Group One">
                                    <el-menu-item index="1-1">item one</el-menu-item>
                                    <el-menu-item index="1-2">item two</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group title="Group Two">
                                    <el-menu-item index="1-3">item three</el-menu-item>
                                </el-menu-item-group>
                                <el-sub-menu index="1-4">
                                    <template #title>item four</template>
                                    <el-menu-item index="1-4-1">item one</el-menu-item>
                                </el-sub-menu>
                            </el-sub-menu>
                            <el-menu-item index="5" disabled>
                                <el-icon><document /></el-icon>
                                <span>Navigator Three</span>
                            </el-menu-item>
                            <el-menu-item index="4">
                                <el-icon><setting /></el-icon>
                                <span>Navigator Four</span>
                            </el-menu-item>
                        </el-menu>
            </el-aside>
            <el-main>
                <router-view v-slot="{ Component }">
                    <transition name="el-fade-in-linear">
                        <component :is="Component" />
                    </transition>
                </router-view>
            </el-main>
        </el-container>
    </el-container>
  </div>


</template>

<style scoped>

.submenu{
    background-color:#181e32;
}
.flex-grow {
    flex-grow: 1;
}

</style>

<script setup>

import {ElMessage } from "element-plus";
import {get} from "@/net";
import router from "@/router";
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'
import {ref} from "vue";
// const handleOpen = (key: string, keyPath: string[]) => {
//     console.log(key, keyPath)
// }
// const handleClose = (key: string, keyPath: string[]) => {
//     console.log(key, keyPath)
// }

const logout = () => {
        get('/api/auth/logout',(message)=>{
            ElMessage.success(message)
            router.push('/')
        })
}

const activeIndex = ref('1')
</script>