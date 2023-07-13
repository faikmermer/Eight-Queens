public class QueensProblem{
	static final int SIZE = 8;

	public static void main(String[] args) {

		QueensProblem qp = new QueensProblem();
		
		

		int chess[][] = new int[SIZE][SIZE];
		int queens = 0;
		int row = 0;
		// ilk satırı rastgele kendimiz bir sütun seçiyoruz. Kendiniz değiştirebilirsiniz.
		chess[0][2] = 1;
		qp.forbiddenMove(row, 4, queens + 1, chess);
		qp.eigQueens(row + 1, queens + 1, chess);
		qp.printfChess(chess);


	}

	boolean safe(int x, int y, int chess[][]){

		return (chess[x][y] == 0);
	}

	int eigQueens(int row, int queens, int chess[][]){

		if(queens == 8){
			return 1;
		}

		for(int i = 0; i < SIZE; i++){
			if(safe(row, i, chess)){
				chess[row][i] = ++queens;
				forbiddenMove(row, i, queens, chess);

				if(eigQueens(row + 1, queens, chess) == 1){
					return 1;
				}
				//Bir sonraki vezir için konum alanı yoksa bir önceki veziri kaldır.
				chess[row][i] = 0;
				//backtrack(Geri dönüşüm ile diğer hamleler hesaplanır)
				backtrack(row, queens, chess);
				--queens;

			}
		}
		return 0;
 
	}

	void backtrack(int row, int queens, int chess[][]){
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				if(chess[i][j] == -queens){
					chess[i][j] = 0;
				}
			}
		}
	}

	void forbiddenMove(int row, int col, int queens, int chess[][]){
		
		//aynı sütuna denk gelmemesi için vezirin harelet alanını işaretliyoruz.
		for(int i = row + 1; i < SIZE; i++){
			if(chess[i][col] < 0 || chess[i][col] > 0){
				continue;
			}
			chess[i][col] = -queens;
		}

		//satir hareket alanı
		for(int i = 0; i < SIZE; i++){
			if(chess[row][i] < 0 || chess[row][i] > 0){
				continue;
			}
			chess[row][i] = -queens;
		}

		// sağ carpraz alanı
		int jp = col + 1;
		
		for(int i = row + 1; i < SIZE; i++){
			if(jp >= 8){
				break;
			}
			if(chess[i][jp] < 0){
				++jp;
				continue;
			}
			chess[i][jp] = -queens;
			++jp;
		}

		//sol carpraz alanı
		int jn = col - 1;

		for(int i = row + 1; i < SIZE; i++){
			if(jn < 0){
				break;
			}
			if(chess[i][jn] < 0){
				--jn;
				continue;
			}
			chess[i][jn] = -queens;
			--jn;
		}

	}

	void printfChess(int chess[][]){
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				if(chess[i][j] < 0){
					chess[i][j] = 0;
				}
				System.out.format("%4d", chess[i][j]);
			}
			System.out.println("");
		}
	}

}