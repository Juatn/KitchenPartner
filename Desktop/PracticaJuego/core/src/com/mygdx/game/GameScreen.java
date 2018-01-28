package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Entidades.Grisacius;
import com.mygdx.game.Entidades.Maullido;
import com.mygdx.game.Entidades.Queso;
import com.mygdx.game.Entidades.RataNormal;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static com.mygdx.game.Constantes.MAX_RATS_SPAWN_TIME;
import static com.mygdx.game.Constantes.MIN_RATS_SPAWN_TIME;
import static com.mygdx.game.Constantes.WIDTH;

/**
 * Created by juana on 23/01/2018.
 */

public class GameScreen extends BaseScreen {


    private Stage stage;
    private World world;
    private Grisacius grisacius;

    public Random random;
    public float ratSpamTime;
    private Music bgMusic;
    private List<RataNormal> listaRatasActivas;
    ArrayList<RataNormal> listaRatasEliminadas;
    private List<Queso> listaQuesos = new ArrayList<Queso>();
    private ArrayList<Maullido> listaMaullidos;
    protected ListIterator<RataNormal> itr;


    public GameScreen(MainGame game) {
        super(game);
        this.game = game;

        bgMusic = game.getManager().get("spazzmatica.ogg");
        stage = new Stage(new FitViewport(1280f, 720f));
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new ContactListener() {
            private boolean hanColisionado(Contact contact, Object userA, Object userB) {
                Object userDataA = contact.getFixtureA().getUserData();
                Object userDataB = contact.getFixtureB().getUserData();

                if (userDataA == null || userDataB == null) {
                    return false;
                }

                return (userDataA.equals(userA) && userDataB.equals(userB)) || (userDataA.equals(userB) && userDataB.equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (hanColisionado(contact, "Maullido", "Rata")) {

                    for (RataNormal c : listaRatasActivas) {
                        if (c.body == contact.getFixtureA().getBody()) {
                            listaRatasEliminadas.add(c);

                        }
                    }

                }
            }

            @Override
            public void endContact(Contact contact) {


            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        random = new Random();
        ratSpamTime = random.nextFloat() * (MAX_RATS_SPAWN_TIME - MIN_RATS_SPAWN_TIME) + MIN_RATS_SPAWN_TIME;


    }

    @Override
    public void show() {
        //Pedimos las texturas al Asset manager
        //stage.setDebugAll(true);

        Texture texturaGrisacius = game.getManager().get("gatoo1.png");
        Texture quesotexture = game.getManager().get("queso.png");
        Texture texturarata = game.getManager().get("ratacartoon1.png");
        Texture maullidotexture = game.getManager().get("maullido.png");
        listaRatasActivas = new ArrayList<RataNormal>();
        listaRatasEliminadas = new ArrayList<RataNormal>();
        listaMaullidos = new ArrayList<Maullido>();


        //Instanciamos las clases
        grisacius = new Grisacius(world, texturaGrisacius, new Vector2(1.6f, 3));
        listaQuesos.add(new Queso(world, quesotexture, new Vector2(0.8f, 6.5f)));
        listaQuesos.add(new Queso(world, quesotexture, new Vector2(0.8f, 5)));
        listaQuesos.add(new Queso(world, quesotexture, new Vector2(0.8f, 3.5f)));
        listaQuesos.add(new Queso(world, quesotexture, new Vector2(0.8f, 2)));
        listaQuesos.add(new Queso(world, quesotexture, new Vector2(0.8f, 0.5f)));
        stage.addActor(grisacius);
        for (Queso x : listaQuesos) {
            stage.addActor(x);
        }
        bgMusic.setVolume(0.75f);
        bgMusic.play();

    }

    @Override
    public void hide() {
        grisacius.desacoplar();
        grisacius.remove();
        for (RataNormal c : listaRatasActivas) {
            c.desacoplar();
            c.remove();
        }
        for (Queso x : listaQuesos) {
            x.desacoplar();
            x.remove();
        }
        bgMusic.stop();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        stage.act(delta);

        //SPAWN RATAS
        ratSpamTime -= delta;
        if (ratSpamTime <= 0) {
            ratSpamTime = random.nextFloat() * (MAX_RATS_SPAWN_TIME - MIN_RATS_SPAWN_TIME) + MIN_RATS_SPAWN_TIME;
            listaRatasActivas.add(new RataNormal(this.world, new Texture("ratacartoon1.png"), random.nextInt((int) (WIDTH - 1))));
            for (RataNormal c : listaRatasActivas) {
                stage.addActor(c);

            }
        }
        // SPAWN MAULLIDOS
        if (Gdx.input.isTouched()) {

            listaMaullidos.add(new Maullido(this.world, new Texture("maullido.png"), grisacius));
            for (Maullido maullido : listaMaullidos) {
                stage.addActor(maullido);
            }

        }

        itr = listaRatasEliminadas.listIterator();

        while (itr.hasNext()) {

            RataNormal x = itr.next();

            stage.getRoot().removeActor(x);
            x.remove();

        }
        Constantes.MIN_RATS_SPAWN_TIME -= 0.00001f;


        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }


}
