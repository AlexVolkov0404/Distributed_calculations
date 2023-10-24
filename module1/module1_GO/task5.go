package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Client struct {
	name string
	task int
}

type Cashbox struct {
	name       string
	clientChan chan Client
}

func (c Client) String() string {
	return c.name
}

func (cb Cashbox) String() string {
	return cb.name
}

func NewCashbox(name string, clientChan chan Client) Cashbox {
	return Cashbox{name: name, clientChan: clientChan}
}

func (cb Cashbox) start(wg *sync.WaitGroup) {
	defer wg.Done()
	for {
		client, ok := <-cb.clientChan
		if !ok {
			return
		}

		fmt.Printf("%s started to help %s\n", cb, client)
		time.Sleep(time.Second * time.Duration(client.task))
		fmt.Printf("%d was done %s by %s\n", client.task, client, cb)
	}
}

func main() {
	rand.Seed(time.Now().UnixNano())

	var wg sync.WaitGroup
	clientChan := make(chan Client, 10)

	cashboxes := []Cashbox{}
	for i := 0; i < 3; i++ {
		cashbox := NewCashbox(fmt.Sprintf("Cashbox %d", i), clientChan)
		cashboxes = append(cashboxes, cashbox)
		wg.Add(1)
		go cashbox.start(&wg)
	}

	for i := 0; i < 10; i++ {
		client := Client{name: fmt.Sprintf("Client %d", i), task: rand.Intn(10)}
		clientChan <- client
	}

	close(clientChan)
	wg.Wait()

	fmt.Println("All is done!")
}
