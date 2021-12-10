# Modern Angular App - setup Jest, Cypress, ESLint, Prettier
The steps bellow are based on this [Source](https://medium.com/@rjnay1984/creating-a-modern-angular-app-with-jest-cypress-eslint-and-prettier-75411086872e).

### Add ESLint
`ng add @angular-eslint/schematics`

### Add jest and remove Karma

`ng add @briebug/jest-schematic`

`npm uninstall -D @types/jasmine jasmine-core jasmine-spec-reporter karma-coverage`

In **jest.config.js** add the following line: testMatch: `["<rootDir>/src/**/*.spec.ts"]`

Note: This is important once we install cypress, so jest doesn’t try to test those specs as well

### Add Cypress:
`ng add @briebug/cypress-schematic`

### Add Prettier with airbnb styleguide:
`npm i -D eslint-config-airbnb-typescript eslint-plugin-import eslint-config-prettier eslint-plugin-prettier prettier`

#### Add **.prettierrc** file:

`{
  "singleQuote": true,
  "arrow-body-style": "off",
  "prefer-arrow-callback": "off",
  "printWidth": 120,
  "overrides": [
    {
      "files": "*.html",
      "options": {
        "parser": "html"
      }
    },
    {
      "files": "*.component.html",
      "options": {
        "parser": "angular"
      }
    }
  ]
}`

#### Add **.prettierignore** file:

`/dist`

`/coverage`

### Modify .eslintrc.json file
See commented lines - ask Stefi please

### Lint, Test, e2e
`npm run lint`

`npm run test`

`npm run e2e`

**Note:** if npm run test fails refer to [this post](https://gitmemory.cn/repo/briebug/jest-schematic/issues/72).




# ANGULAR ARCHITECTURE

## LAYERED ANGULAR ARCHITECTURE
Goal: decomposing the system through abstraction layers to achieve loose-coupling, abstraction and separation of concerns, unidirectional data flow, reactive state management.

Sources:
[Architecting Enterprise Angular Application](https://medium.com/@getrohith.sathya/architecting-enterprise-angular-application-3276ac496c02)
and
[Angular Architecture Patterns and Best Practices (that help to scale)](https://dev-academy.com/angular-architecture-best-practices/)


## Horizontal division - three abstarction layers

### Presentation Layer (bottom) 
* presents the UI and delegates user’s actions to the core layer, through the abstraction layer)
* depends on the Facade layer, to get data and it should never directly interact with core layer

##### Dumb/Presentational Components
* do not have intelligence of their own
* depend on parent component to give them data (via Inputs)
* pass any user interaction back to parent component (via Outputs)

##### Smart/Container Components
* wrap one or more dumb/presentational components
* responsible for providing the data and handling the interactions from children component
* know where to get the data from and how to handle the events from children component
* interact with Facade layer

### Abstraction Layer (middle) 
* facade -  decouples the presentation layer from the core layer
* handles communication between presentation and core layer
* exposes the state data for the components in the presentational layer
* delegate logic execution to the core layer
* decide about data synchronization strategy (optimistic vs. pessimistic)
* might cache data from external API

##### Synchronization strategy
* **Optimistic update** changes the UI state first and then attempts to update the backend state. This provides a user with a better experience, as he does not see any delays, because of network latency. If backend update fails, then UI change has to be rolled back.
*	**Pessimistic update** changes the backend state first and only in case of success updates the UI state. Usually, it is necessary to show some kind of spinner or loading bar during the execution of backend request, because of network latency.

### Core Layer (top)
* contains application core logic - state management and async service
* responsible for all the data manipulation and outside world communication via APIs

##### State management
* we can pick any state management library that support RxJS (like NgRx) or simply use BehaviorSubjects. We can start with BehaviorSubjects to manage the state, and later if there is a need to replace State management with some other library, without impacting any other parts of the application
* state objects are immutable and they are returned by a pure function.

##### Async/API Service
* have only one responsibility, to communicate with API(REST) end points and nothing else
* avoid any caching, business logic or data manipulation here
* don’t let the async service know about the state management logic

![Diagram](https://dev-academy.com/angular-architecture-best-practices/flowAbstract.gif)


## Vertical division - modular design
* vertical separation into feature modules
* each feature module shares the same horizontal separation of the core, abstraction, and presentation layer
* feature modules could be lazily loaded (and preloaded) into the browser

##### Two additional modules:
-	CoreModule - defines our singleton services, single-instance components, configuration, and export any third-party modules needed in AppModule. This module is imported only once in AppModule. 
-	SharedModule - contains common components/pipes/directives and also export commonly used Angular modules (like CommonModule). SharedModule can be imported by any feature module.

# Folder structure

##### Core Module
* core module is to be imported ONLY in the app module - to prevent re-importing the module elsewhere, we should add a module-import-guard in it’s constructor method
* contains only services that are singleton and reused in more than one feature module in the application
* a good place to add route guards and http interceptors
* constants, enums, utils, and universal data models (interfaces)
* might contain static components like the toolbar/navbar and footer

##### Shared Module
* contains all components, directives and pipes that are shared and used in multiple feature modules or the app.module.ts
* shared module will be imported in any feature module that requires the shared components
* shared module may be imported in app module as well if the app module needs a component from the shared module
* shared module shouldn’t have any dependency to the rest of the application, and should therefore not rely on any other module

##### Feature Modules
* all feature areas are in their own folder, with their own module
* feature modules deliver user experience dedicated to a particular application feature
* contains a top component that acts as the feature root and private, supporting sub-components descend from it (smart and dump components)

##### Styles Folder
* contains mixins or css-functions, responsible for their own areas


#### Example

├── src
  
    ├── core                   
    
        ├── services (async, store, facade)                      
    
        ├── router-guards                  
    
        ├── interceptors

        ├── components                         
        
        ├── constants        

        ├── enums   

        ├── models

        ├── utils                 
    
        └── core.module.ts 

    ├── shared                   
    
        ├── components                      
    
        ├── directives                
    
        ├── pipes             
    
        └── shared.module.ts     

    ├── styles                   
    
        ├── _colors.scss                      
    
        ├── _utils.scss                          
    
        └── _index.scss    

    ├── features                   
    
        ├── feature-A  

          ├── components (dump components)  

          ── feature-a-routing.module.ts  

          ── feature-a.module.ts

          ── feature-a.component.html|scss|ts (smart component)                 
    
        ├── feature-B            
    
        ...            
    
        └── feature-C  

    └── app.module.ts      

