package main

import (
	"container/list"
	"fmt"
	"math/rand"
)

func fight(a int, b int, o chan int) {
	if rand.Intn(2) == 1 {
		o <- b
	} else {
		o <- a
	}
}

func main() {
	l := list.New()
	l.Init()

	for i := 0; i < 32; i++ {
		l.PushBack(0)
		l.PushBack(1)
	}

	for l.Len() > 1 {
		o := make(chan int)

		val := l.Front()

		for val != nil {
			go fight(val.Value.(int), val.Next().Value.(int), o)
			val = val.Next().Next()
		}

		iterations := l.Len() / 2

		l = list.New()
		l.Init()

		for i := 0; i < iterations; i++ {
			l.PushBack(<-o)
		}
	}

	fmt.Printf("Winner %d", l.Front().Value.(int))
}
