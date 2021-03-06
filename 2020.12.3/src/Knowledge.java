/**
 * @program: 2020.12.3
 * @description:  计算机原理和操作系统概述
 * @author: spdz
 * @create: 2020-12-03 18:20
 **/

/**
 * 1.理解硬件上，计算机是如何工作的
 *  1.冯诺依曼体系
 *  2.本质上
 *  3.CPU 分为
 *  4.硬盘
 * 2.操作系统
 *  1.概念
 *  2.为什么需要：进程越来越多，越来越复杂
 *  3.操作系统使用分层管理
 *  4.进程
 *  5.CPU 的切换
 */

/**
 * 计算机体系结构
 *  冯诺依曼体系（硬件）：
 *      1.处理器（Processer）（CPU）
 *      2.存储器（Storage）（内存）
 *      3.输入设备（Input Device）
 *      4.输出设备（Output Device）
 *  input/output/processer 都只能和 storage 打交道
 *  所谓的 storage 就是 内存
 *  所谓的 硬盘 这类外存，被视为 input 和 output
 *  本质上，计算机就是在做 数据流 的加工工作
 */

/**
 * 进程和程序（操作系统里的概念）
 *  程序（Program） 是一组可以被执行的文件
 *      （主要是一些 指导CPU 如何工作的 操作说明）（静态的东西） （相当于 做菜的菜单）
 *  进程（Process） 用户视角-- 是遵照 程序完成 运行 的一次过程
 *                  OS视角-- 资源分配的最小单位
 *
 *  一份程序，可以同时有多个进程在运行
 *  程序必须以进程为表现，才能运行起来
 */

/**
 * 计算机 是 硬件（Hardware）+ 软件（Software） 配合工作
 *  硬件：冯诺依曼体系
 *  软件：指导硬件如何工作
 */

/**
 * 操作系统（0peration System OS）
 *  管理计算机的 软件，管理硬件 + 软件概念，本质上 做资源的协调分配
 *  计算机上同时需要运行的程序非常复杂，需要专门做管理
 *
 *  OS 是做管理的软件。
 *      主要做的是 资源（资源是指 硬件：CPU，内存，硬盘，屏幕等）的 协调和分配工作
 *      资源分配的单位：进程
 *          进程 是 资源分配 的最小单位（除了CPU）
 *          线程 是 调度 的最小单位（CPU 资源分配的最小单位）
 *
 * 操作系统使用分层管理
 *   硬件--》驱动--》OS内核--》提供的接口--》JVM--》应用
 *
 *   驱动 中 有硬件 和 软件
 *   OS内核 + 提供的接口 = 操作系统
 *   硬件 + 驱动（一半）= 硬件
 *   驱动（一半） + OS内核 + 提供的接口 + JVM + 应用 = 软件
 */

/**
 * 两个最主要资源
 *  内存：以空间为单位进行分配
 *  CPU：假设是单核CPU  以时间为单位进行分配
 */

/**
 * CPU：
 *  分为：
 *  逻辑运算单元（ALU）：
 *      里面有 寄存器（很小）：CPU能直接读取的位置
 *  控制单元（CU）：
 *      里面有 PC（程序计数器）：中保存的是要求 CPU 执行的 下一条指令的 地址
 *                  控制 PC 就能控制 CPU 做什么
 *             IR（指令寄存器）：中保存的是根据 PC 读取的，下一条要执行的 指令
 *
 *  程序都加载 到 内存中
 *  指令（也就是 程序）都在内存中
 *
 *  CPU 的一个周期：
 *      1.控制单元根据 PC 中的地址 读取 下一条指令，保存到 IR 中
 *      2.更新 PC 值
 *      3.逻辑运算单元执行 IR 中的 指令
 */

/**
 * 一个*.java 文件的历程
 *  编写 *.java 文件 通过 硬盘 输入给 内存,CPU 按照 javac 进程（编译器进程）的指令
 *      读取内存中的 *.java ，转换为 *.class 放进内存
 *      若要将文件给其他用户，可通过 磁盘将 *.class文件 输出给 用户的 磁盘，用户安装文件，
 *      将文件 输入 内存，CPU 根据 *.class 的指令运行，放回内存，在屏幕输出
 */

/**
 *  *.class文件中 是 字节码，CPU 并不认识，还要经过 JVM 再编译
 *
 *  Windows 只认 exe/bat
 *  Linux 只认 elf/sh
 */

/**
 * 进程的调度 -- CPU 的切换
 *  线程 是 调度 的最小单位（是CPU 资源分配的最小单位）
 * 什么情况下会把一个进程从 CPU 上切换下来
 *  1.有另一个 更重要的任务（进程） 到来，发生了 抢占
 *  2.进程指令结束，进程运行结束了
 *  3.进程内部的 指令 主动放弃了 CPU
 *  4.进程内部执行到了 需要等待的 IO（输入/输出） 指令时，会放弃 CPU，等待 IO 完成
 *  5.分配的 时间片 耗尽
 */

/**
 * 时间片
 *      操作系统（如Windows、Linux）的任务调度是采用 时间片轮转 的抢占式调度方式，
 *  也就是说一个任务执行一小段时间后强制暂停去执行下一个任务，每个任务轮流执行。
 *      任务执行的一小段时间叫做 时间片，任务正在执行时的状态叫运行状态，
 *  任务执行一段时间后强制暂停去执行下一个任务，被暂停的任务就处于就绪状态等待下一个属于它的时间片的到来。
 */

public class Knowledge {
}
