class Logger:

    def init(self):
        self.map = {}

    def shouldPrintMessage(self, timestamp, message):
        if message not in self.map.keys() or timestamp - self.map[message] >= 10: 
            self.map[message] = timestamp
            return True
        return False