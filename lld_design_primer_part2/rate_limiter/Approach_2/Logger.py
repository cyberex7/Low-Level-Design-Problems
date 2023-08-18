class Logger:
    def __init__(self):
        self.nextEligibleTime = {}

    def shouldPrintMessage(self, timestamp, message):
        if timestamp < self.nextEligibleTime.get(message, 0):
            return False
        self.nextEligibleTime[message] = timestamp + 10
        return True
