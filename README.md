My goal was to write code that is easily maintainable, testable and reusable.
I have used a single-activity app pattern + MVP architecture and reactive programming.
Presenters observe directly the Database that is updated by the model. Views don't know anything about the underneath data layer.

The idea is that the model takes a stream of Actions, do whatever operation is needed and then returns a stream of Results.
In our case, the steam of actions doesn't come from the view (as consequence of ui interaction) but it comes from RefreshBusRoutesLooperProvider class.
What if the first time the app is open there isn't internet connection? Or, more generally, how do we have to refresh this data? And how often?
RefresjBusRoutesLooperProvider wraps this kind of logic and provides an observable that emits new action whenever the list of routes should be refreshed.

In this sample app, presenters are basically only interest in data coming from the DB (list of routes, list of stops...) excepts for RoutesListPresenter that
is responsible to tell to RoutesListController to display empty views and hide the recycler view in case where either no data are available or there is no connection.

To achieve this, the stream of results is mapped into a state by RefreshBusRoutesStateBinder that provides the state as observable to the presenter.
Different presenters can reuse the same binder if they want to share the state across multiple screens.

Different layer of the app are now decoupled and testing becomes quite simple.