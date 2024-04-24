# Dots and Boxes
---



本项目为完全由个人开发的小项目，主要语言为Java，实现的是一种名为Dots_and_Boxes（点格棋）的游戏。

想做该项目的原因是跟着课程做了一些有一定抽象程度的项目，从中学习了一些构建小游戏库的结构组织方法（但没有系统学游戏设计），加上最近在Nintendo Switch上玩到了D&B这个游戏，所以有了这个项目！



本项目的游戏逻辑结构已经完成，也做了在终端显示的可视化，后续也会为本项目实现一个GUI的。

以下是WIKI对其的简介：

> **Dots and boxes** is a [pencil-and-paper game](https://en.wikipedia.org/wiki/Pencil_and_paper_game) for two players (sometimes more). It was first published in the 19th century by French mathematician [Édouard Lucas](https://en.wikipedia.org/wiki/Édouard_Lucas), who called it ***la pipopipette\***.[[1\]](https://en.wikipedia.org/wiki/Dots_and_boxes#cite_note-1) It has gone by many other names,[[2\]](https://en.wikipedia.org/wiki/Dots_and_boxes#cite_note-ww-2) including the **dots and dashes**, **game of dots**,[[3\]](https://en.wikipedia.org/wiki/Dots_and_boxes#cite_note-3) **dot to dot grid**,[[4\]](https://en.wikipedia.org/wiki/Dots_and_boxes#cite_note-4) **boxes**,[[5\]](https://en.wikipedia.org/wiki/Dots_and_boxes#cite_note-5) and **pigs in a pen**.[[6\]](https://en.wikipedia.org/wiki/Dots_and_boxes#cite_note-6)
>
> ​												——Wikipedia







## 游戏规则

- 游玩人数：2人
- 游戏玩法：回合制下棋玩法，棋盘为m*n的格子矩阵，玩家在各自回合可以占据某个格子的某条边（自选），有所属的边不能再次被选择
- 得分规则：玩家初始分数都为0，当玩家在其回合内占据一条边后，如果该条边封闭了一个格子（即其他三条边在之前已被占据，且三条边不必属于一个玩家），那么这个格子归属于该玩家，该玩家分数加一。
- 特殊规则：当玩家得分后（即刚封闭了一个格子），则再获得一次占据某条边的机会。
- 结束条件：当棋盘上格子全部有所属后，游戏结束。
- WINNER：游戏结束时，得分高的玩家获胜。





## 操作方法

**对于终端可视化版本**：


以下是一个简单的演示：

<img src="https://github.com/Lyon673/DotsAndBoxes/blob/master/DandB.gif" width="450" alt="TerminalVersion"/>

属于玩家1的点和格用红色表示，属于玩家2的点和格用蓝色表示

当玩家在选择占据哪条边时，移动路径上的边用黄色标明



<font color=red>Player1</font>：

- `i`键：选择边时向上移动
- `k`键：选择边时向下移动
- `j`键：选择边时向左移动
- `l`键：选择边时向右移动
- `p`键：选定边

<font color=blue>Player2</font>：

- `w`键：选择边时向上移动
- `s`键：选择边时向下移动
- `a`键：选择边时向左移动
- `d`键：选择边时向右移动
- `t`键：选定边





## 项目结构

1. `Main.java`

   顶层设计模块，捕捉游戏过程中的错误，游戏结束后退出。提供GUI接口。

2. `Game.java`

   控制游戏过程，包括回合制玩法的实现，特殊规则的实现，对键盘输入的处理

3. `Model.java`

   主要的逻辑实现，包括选定边的操作和选择前的移动实现，终端可视化函数也在其中

4. `Board.java`

   棋盘类，存储了棋盘的信息，包括点数组和格子数组

5. `GUI.java`,`GUISource.java`,`InputSource.java`

   提供的GUI接口，截止目前还没有实现

6. `CLS.java`

7. `Box.java`

8. `Edge.java`

9. `Player.java`

10. `Side.java`
