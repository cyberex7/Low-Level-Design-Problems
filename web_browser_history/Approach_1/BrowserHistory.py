class BrowserHistory:
    def __init__(self, homepage):

        self.head = Node(self, homepage)
        self.tail = self.head
        self.current = self.head

    def visit(self, url):
        newNode = Node(self, url)
        newNode.next = self.current
        self.current.prev = newNode
        self.head = newNode
        self.current = self.head

    def forward(self, steps):
        node = self.current
        while node.prev is not None and steps > 0:
            node = node.prev
            steps -= 1
        self.current = node
        return node.url

    def back(self, steps):
        node = self.current
        while node.next is not None and steps > 0:
            node = node.next
            steps -= 1
        self.current = node
        return node.url


class Node:

    def __init__(self, outerInstance, url):
        self.url = None
        self.prev = None
        self.next = None

        self._outerInstance = outerInstance

        self.url = url

    
