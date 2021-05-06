package mc322.lab05b;

public class Tabuleiro {
    private String estado;
    private Peca[][] tab;
    private boolean ultimoMovimentoValido = false;

    public Tabuleiro() {
        estado = "";
		tab = new Peca[8][8];
		
		boolean presenca = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {	
                
                // Inicializa os peões
				if (presenca) {
					if (i < 3) {
						tab[i][j] = new Peao(i, j, 'b');
					}
					else {
						tab[i][j] = new Peao(i, j, 'p');
					}
				}
				else {
					tab[i][j] = null;
				}
				
                // Atualiza o valor do boolean presenca
				if (j == 7 || i == 3 || i == 4) {
					continue;
				}
				else {
					presenca = (presenca) ? false : true;
					continue;
				}
			}
		}
		atualizaEstado();
    }

    public void atualizaEstado() {
        estado = "";
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {
				if (tab[i][j] != null) {
					estado += tab[i][j].getCor();
				}
				else {
					estado += '-';
				}
			}
			estado += '\n';
		}
    }

    public String getEstado() {
        return estado;
    }

    public boolean coordenadasValidas(int i, int j) {
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			return true;
		}
		return false;
	}

    // Constrói um vetor de char com as peças pelas quais o caminho passa, excluindo
    // a origem e incluindo o destino. Já assume que o movimento é diagonal.
    public char[] construirTrajeto(int iOrig, int jOrig, int distVert, int distHoriz) {
        int iPasso = distVert/Math.abs(distVert);
		int jPasso = distHoriz/Math.abs(distHoriz);
		int iMeio = iOrig + iPasso, jMeio = jOrig + jPasso;
		char[] trajeto = new char[Math.abs(distVert)]; 
		int indice = 0;
		while (indice < trajeto.length) {	
			if (tab[iMeio][jMeio] != null) {
				trajeto[indice] = tab[iMeio][jMeio].getCor();
			}
			else {
				trajeto[indice] = '-';
			}
			iMeio += iPasso;
			jMeio += jPasso;
			indice++;
		}
		return trajeto;
    }
    
    // Retira a peca origem, move para a casa destino, retira peça capturada (se houver).
    public void processaMovimento(int iOrig, int jOrig, int iDest, int jDest, 
    int[] coordVitima) {
        char pOrigem = tab[iOrig][jOrig].getCor();
        if (iDest == 7 || iDest == 0 || Character.isUpperCase(pOrigem)) {
            tab[iDest][jDest] = new Dama(iDest, jDest, Character.toUpperCase(pOrigem));
        }
        else {
            tab[iDest][jDest] = new Peao(iDest, jDest, pOrigem);
        }
        if (coordVitima != null) {
            tab[coordVitima[0]][coordVitima[1]] = null;
        }
        tab[iOrig][jOrig] = null;
    }

    // Percorre o trajeto e devolve as coordenadas de uma possível peça vítima.
    public int[] verificaVitima(int iOrig, int jOrig, int distVert, int distHoriz) {
        int iPasso = distVert/Math.abs(distVert);
		int jPasso = distHoriz/Math.abs(distHoriz);
		int iMeio = iOrig + iPasso, jMeio = jOrig + jPasso;
		int[] coordVitima = null;
		int passos = Math.abs(distVert);
		while (passos > 0) {	
			if (tab[iMeio][jMeio] != null) {
                coordVitima = new int[2]; 
				coordVitima[0] = iMeio;
                coordVitima[1] = jMeio;
                break;
			}
			iMeio += iPasso;
			jMeio += jPasso;
			passos--;
		}
		return coordVitima;
    }

    public void solicitaMovimento(String comando) {
        String origem = comando.substring(0, 2);
		String destino = comando.substring(3, 5);
		int iOrig = Integer.parseInt(origem.substring(1, 2)) - 1;
		int jOrig = (int)origem.charAt(0) - 97;
		int iDest = Integer.parseInt(destino.substring(1, 2)) - 1;
		int jDest = (int)destino.charAt(0) - 97;
        boolean valido = false;

        // 1) Verifica se as coordenadas são válidas
        if (coordenadasValidas(iOrig, jOrig) && coordenadasValidas(iDest, jDest)) {
            // 2) Verifica se tem peça na origem
            if (tab[iOrig][jOrig] != null) {
                int distVert = iDest - iOrig;
			    int distHoriz = jDest - jOrig;
                // 3) Verifica se movimento é diagonal
                if (Math.abs(distVert) == Math.abs(distHoriz)) {
                    char[] trajeto = construirTrajeto(iOrig, jOrig, distVert, distHoriz);
				    Peca pOrigem = tab[iOrig][jOrig];
                    // 4) Pergunta à peça se o movimento é valido
                    if (pOrigem.movimentoValido(distVert, trajeto)) {
                        int[] coordVitima = verificaVitima(iOrig, jOrig, distVert, distHoriz);
                        processaMovimento(iOrig, jOrig, iDest, jDest, coordVitima);
                        atualizaEstado();
                        valido = true;
                    }
                }
            }
        }
        ultimoMovimentoValido = valido;
        imprimirTabuleiro(origem, destino, valido);
    }

    public void imprimirTabuleiro(String origem, String destino, boolean valido) {
        if (!valido) {
			System.out.println("Movimento invalido!\n");
            return;
        }
        else if (origem == null && destino == null) {
			System.out.println("Tabuleiro inicial: ");
		}
		else {
			System.out.println("origem: " + origem);
			System.out.println("destino: " + destino);
		}
		int n = 8;
		int i = 0;
		do {
			System.out.print(n);
			n--;			
			for (int j = i; j < i + 9; j++) {
				System.out.print(" " + estado.charAt(j));
			}
			i += 9;
		} while (i < estado.length());
		System.out.println("  a b c d e f g h\n");
    }

    // Converte as coordenadas i e j da matriz em uma string do tipo a1, b2 etc
    public String converterCoordenadas(int i, int j) {
        String linha = Integer.toString(8 - i);
        char coluna = (char)(j + 97);
        return coluna + linha;
    }

    // Gera o vetor de strings do tipo a1_, a2b, a3p etc
    public String[] gerarEstados() {
        String estados[] = new String[64];
        int k = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 7; i >= 0; i--) {
                String casa = "";
                casa += converterCoordenadas(i, j);
                if (tab[i][j] != null) {
                    casa += tab[i][j].getCor();
                }
                else {
                    casa += "_";
                }
                estados[k] = casa;
                k++;
            }
        }
        return estados;
    }

    public void exportarArquivo(String caminho) {
        String[] estados;
        if (ultimoMovimentoValido) {
            estados = gerarEstados();
        }
        else {
            estados = new String[1];
            estados[0] = "erro";
        }
        CSVHandling csv = new CSVHandling();
        csv.setDataExport(caminho);
        csv.exportState(estados);
    }

}
