from Board import Board, GameState


class Game:
    def __init__(self, boardRows, boardColumns, numberOfBombs):
        self._board = None
        self._boardRows = boardRows
        self._boardColumns = boardColumns
        self._numberOfBombsInTheGame = numberOfBombs
    def _initialize(self):
        if self._board is None:
            self._board = Board(self._boardRows, self._boardColumns, self._numberOfBombsInTheGame)
            self._board.printBoard()
    def start(self):
        if self._board is None:
            self._initialize()
        self._playGame()
    def _playGame(self):
        scanner = input()
        gameState = GameState.INPROGRESS
        while gameState is GameState.INPROGRESS:
            inputi = input()
            if inputi == "exit":
                return
            row = int(inputi.split("-")[0])
            col = int(inputi.split("-")[1])
            gameState = self._board.exposeCell(row, col)
            if gameState is GameState.LOST:
                print("FAIL")
            elif gameState is GameState.WON:
                print("WIN")
            self._board.printBoard()
    

    
