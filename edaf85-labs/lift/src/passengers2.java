import lift.Passenger;

public class passengers2 {
    private monitor2 mon;

    public passengers2(monitor2 view) {
        mon = view;
    }

    Thread passenger1 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger2 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger3 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger4 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger5 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger6 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger7 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger8 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger9 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger10 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger11 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger12 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger13 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger14 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger15 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger16 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger17 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger18 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger19 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        Thread passenger20 = new Thread (() -> {
            try {
                while(true) {
                    Passenger pass = mon.getView().createPassenger();
                    pass.begin();
                    int fromFloor = pass.getStartFloor();
                    mon.requestFloor(fromFloor);
                    pass.enterLift();
                    int toFloor = pass.getDestinationFloor();
                    mon.requestExit(toFloor);
                    pass.exitLift();
                    mon.walking();
                    pass.end();
                }
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        });

        public void begin() {
            passenger1.start();
            passenger2.start();
            passenger3.start();
            passenger4.start();
            passenger5.start();
            passenger6.start();
            passenger7.start();
            passenger8.start();
            passenger9.start();
            passenger10.start();
            passenger11.start();
            passenger12.start();
            passenger13.start();
            passenger14.start();
            passenger15.start();
            passenger16.start();
            passenger17.start();
            passenger18.start();
            passenger19.start();
            passenger20.start();
        }

}
