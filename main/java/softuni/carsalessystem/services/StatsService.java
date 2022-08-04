package softuni.carsalessystem.services;

import softuni.carsalessystem.models.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
