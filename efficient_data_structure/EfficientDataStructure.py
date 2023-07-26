
import random


class EfficientDataStructure:
    def __init__(self, size):
        self.arr = [None for _ in range(size)]
        self.map = {}
        self.count = -1
        self.SIZE = size

    def _insert(self, stri):
        self.count += 1
        if self.count >= self.SIZE:
            return False
        self.arr[self.count] = stri
        self.map[stri] = self.count
        return True

    def _delete(self, stri):
        if self.count == 0 & stri in self.map.keys():
            self.arr[self.count] = None
            self.arr = [None] * len(self.arr)
            self.map.pop(stri)
            self.count -= 1
            return True
        if stri not in self.map.keys() or self.count == -1:
            return False
        index = self.map[stri]
        replaceWith = self.arr[self.count]
        self.arr[index] = replaceWith
        self.arr[self.count] = None
        self.count -= 1
        self.map.pop(stri)
        self.map[replaceWith] = index
        return True

    def _delete(self, index):
        if index >= len(self.map):
            return False
        if self.count == 0:
            self.map.pop(self.arr[index])
            self.arr[self.count] = None
            self.arr = [None] * len(self.arr)
            self.count -= 1
            return True
        elementToDelete = self.arr[index]
        replaceWith = self.arr[self.count]
        self.arr[index] = replaceWith
        self.arr[self.count] = None
        self.count -= 1
        self.map.pop(elementToDelete)
        self.map[replaceWith] = index
        return True

    def _contains(self, stri):
        return stri in self.map.keys()

    def _getElementAtIndex(self, index):
        return self.arr[index]

    def _getRandom(self):
        index = self._rand(0, self.count)
        return self.arr[index]

    def _size(self):
        return len(self.map)

    def _returnAll(self):
        i = 0
        while i <= self.count:
            print(self.arr[i] + " ", end='')
            i += 1
        print()
        for stri in self.map.keys():
            print(stri + " ", end='')
        print()

    def _rand(self, beg, end):
        range = end - beg + 1
        return beg + int((random.random() * range))


def main(args):
    ds = EfficientDataStructure(100)
    print("Array Size: " + str(len(ds.arr)))
    print("Size now: " + str(ds._size()))
    ds._insert("Hello")
    ds._insert("World")
    ds._returnAll()
    print("Size after 2 insertions: " + str(ds._size()))
    print("Random Access: " + ds._getRandom())
    print(ds._contains("IamNotThere"))
    print(ds._contains("Hello"))
    ds._delete("Hello")
    print("Size after deletion: " + str(ds._size()))
    print(ds._getElementAtIndex(1))
    ds._returnAll()
    print(ds._delete(0))
    print("Size After Another Deletion: " + str(ds._size()) + " Count: " + str(ds.count))
    ds._returnAll()
