package model;

public class PeopleSimulator {

    public PeopleSimulator(Model model){

        new Thread(() -> {
            String[] peeps = {"Bob", "Wendy"};
            String[] vinylTitles = new String[model.getList().getAlbum().size()];

            for (int z = 0; z < model.getList().getAlbum().size(); z++) {
                vinylTitles[z] = model.getList().getAlbum().get(z).getTitle();
            }

            for (int i = 0; i < 10; i++) {
                String name = peeps[i%2];
                int index = (int) (Math.random() * model.getList().getAlbum().size());

                double rand = Math.random();
                if(rand < 0.33){
                    System.out.println(name + " borrowed " + vinylTitles[index]);
                    try {
                        model.borrowVinyl(vinylTitles[index], name);
                    }
                    catch (IllegalStateException e){}
                } else if (rand > 0.33 && rand < 0.66) {
                    System.out.println(name + " reserved " + vinylTitles[index]);
                    try {
                        model.reserveVinyl(vinylTitles[index], name);
                    }
                    catch (IllegalStateException e){}
                }
                else{
                    System.out.println(name + " returned " + vinylTitles[index]);
                    try {
                        model.returnVinyl(vinylTitles[index], name);
                    }
                    catch (IllegalStateException e){}
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }
}
