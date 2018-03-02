package com.prem.test.jet;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /*Observable<BaseRepository> obs1 = Observable.create(new ObservableOnSubscribe<BaseRepository>() {
        @Override
        public void subscribe(ObservableEmitter<BaseRepository> emitter) throws Exception {

            try {

                emitter.onNext(new DefaultRepository(null,null));
                emitter.onComplete();

            }catch (Exception e){
                emitter.onError(e);
            }

        }
    });

    Observable<BaseRepository> obs2 = Observable.create(new ObservableOnSubscribe<BaseRepository>() {
        @Override
        public void subscribe(ObservableEmitter<BaseRepository> emitter) throws Exception {

            try {

                emitter.onNext(new DefaultRepository(null,null));
                emitter.onComplete();

            }catch (Exception e){
                emitter.onError(e);
            }

        }
    });

    DefaultRepository dr;

    @Before
    public void setUp() throws Exception {
        dr = new DefaultRepository(obs1,null);
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Test
    public void testInvalidUrl() {

        dr.test();
    }*/
}