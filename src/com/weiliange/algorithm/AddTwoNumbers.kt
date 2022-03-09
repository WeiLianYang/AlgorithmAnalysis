package com.weiliange.algorithm

import kotlin.math.pow

/**
 * @author : WilliamYang
 * @date : 2022/3/8 11:36
 * @description : <a href="https://leetcode-cn.com/problems/add-two-numbers">两数相加</a>
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 */

data class ListNode(
    var value: Int = 0,
    var next: ListNode? = null
)

/**
 * 第一种：没有考虑链表长度，存在溢出情况
 * @param l1 链表1
 * @param l2 链表2
 */
fun addTwoNumbers1(l1: ListNode?, l2: ListNode?): ListNode {
    println(l1)
    println(l2)

    println("---------> start <---------")

    val list1 = arrayListOf<Int>()
    val list2 = arrayListOf<Int>()

    flatNodeValue(l1, list1)
    flatNodeValue(l2, list2)

    println(list1)
    println(list2)

    val sum1 = sumNodeValue(list1)
    val sum2 = sumNodeValue(list2)
    val sum = sum1 + sum2

    println("sum1: $sum1, sum2: $sum2, sum: $sum")

    return numberToNode(sum)
}

/**
 * 链表中的节点值展开为集合
 * @param node 链表
 * @param list 集合
 */
fun flatNodeValue(node: ListNode?, list: ArrayList<Int>): ArrayList<Int> {
    if (node == null) {
        return list
    }
    list.add(node.value)
    return flatNodeValue(node.next, list)
}

/**
 * 对链表中的数求和
 * @param list
 */
fun sumNodeValue(list: ArrayList<Int>): Int {
    var sum = 0
    list.forEachIndexed { index, value ->
        val r = 10f.pow(index.toFloat()) * value
        sum += r.toInt()
    }
    return sum
}

/**
 * 将数字转为链表
 */
fun numberToNode(number: Int): ListNode {
    val source = number.toString().reversed()
    println("source: $source")

    var firstNode: ListNode? = null
    var node: ListNode? = null

    source.forEachIndexed { _, char ->
        if (node == null) {
            node = ListNode(char.digitToInt())
            firstNode = node
        } else {
            val nextNode = ListNode(char.digitToInt())
            node?.next = nextNode
            node = nextNode
        }
    }

    println(firstNode)

    return firstNode!!
}

/**
 * 第二种：解决第一种中的溢出情况，其实就是解决大数相加问题：
 *
 * 1. 将对应位的数字逆序相加，然后存到数组中
 * 2. 检查数组中的各项如果 >=10，就将该项 /10=十位上的数，再累加到后一项。将该项对 %10=个位，重新赋值给当前项
 * 3. 最后再逆序组装获得链表
 */
fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode {
    println(l1)
    println(l2)

    println("---------> start <---------")

    val list1 = arrayListOf<Int>()
    val list2 = arrayListOf<Int>()

    flatNodeValue(l1, list1)
    flatNodeValue(l2, list2)

    println(list1)
    println(list2)

    return ListNode()
}


fun main() {
    val node11 = ListNode(1)
    val node12 = ListNode(2, node11)
    val node13 = ListNode(3, node12)

    // [2, 1] + [3, 2, 1] = [5, 3, 1]
    // 12 + 123 = 135
    // ListNode(value=5, next=ListNode(value=3, next=ListNode(value=1, next=null)))
    addTwoNumbers1(node12, node13)
}
