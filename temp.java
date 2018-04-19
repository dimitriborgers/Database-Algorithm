private Scanner x;

    public void openFile() {
        try {
            x = new Scanner(new File("query.txt"));
        }
        catch(Exception e) {
            System.out.println("File not found");
        }
    }

    public void readFile() {
        while (x.hasNext()) {

        }
    }

    public void closeFile() {
        x.close();
    }
