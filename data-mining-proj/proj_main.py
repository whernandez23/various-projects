import pandas as pd
import mlxtend as mlxtend
import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.impute import SimpleImputer
from sklearn.metrics import accuracy_score
from sklearn import preprocessing
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer, make_column_transformer
from sklearn.pipeline import Pipeline, make_pipeline
from sklearn.naive_bayes import BernoulliNB, GaussianNB
from time import time
from sklearn.feature_selection import SelectFromModel

ds = pd.read_csv(r'C:\Users\ninte\Downloads\DataMining_Proj\census-income.test.csv')

df = pd.read_csv(r'C:\Users\ninte\Downloads\DataMining_Proj\census-income.data.csv',
                 na_values=' ?')

X = df.drop(['income'], axis=1)
y = df['income']

X_test = ds.drop(['income'], axis=1)
y_test = ds['income']

#X['sex'] = X.sex.map({'female':1, 'male':0})

numerical_features = X.select_dtypes(include = ['int64', 'float64']).columns.values
categorical_features = X.select_dtypes(include = ['object']).columns.values
cat_impute = SimpleImputer(missing_values=np.NaN, strategy='most_frequent', fill_value='missing_value')

#Simple Imputer imputes missing values for the categorical features
#Replaces them with the most_frequently represented value
#Main Problem: KNNClassifier would not accept categorical variables
#Converting the columns into numerical variables gave worse results
cat_impute.fit(X[categorical_features])
SimpleImputer(fill_value='missing', strategy='most_frequent')

X[categorical_features] = cat_impute.transform(X[categorical_features])

sel = SelectFromModel(RandomForestClassifier(n_estimators=128, max_depth=7))
sel.fit(X[numerical_features], y)

sel.get_support()

selected_features = X[numerical_features].columns[(sel.get_support())]
print(selected_features, end='\n')

#print(X[categorical_features].isnull().sum())

#One Hot Encoding to Address the Categorical Variables
col_trans = ColumnTransformer([
    ("ohe_ignore", OneHotEncoder(handle_unknown ='ignore',sparse_output=False),
    ["workclass", "education",'marital-status','occupation','relationship','race', 'sex','native-country'])],
    remainder='passthrough')

rf_classifier = RandomForestClassifier(n_estimators=128, max_depth=7)

pipe = make_pipeline(col_trans, rf_classifier)

pipe.fit(X, y)

y_pred = pipe.predict(X_test)

print(classification_report(y_test, y_pred, digits=4))
print(confusion_matrix(y_test, y_pred))

D = pd.DataFrame(pipe.transform(X_train), columns=pipe['encoder'].get_feature_names_out())

tic_bwd = time()
sfs_backward = SequentialFeatureSelector(estimator=BernoulliNB(),
    n_features_to_select=100, direction="backward").fit(D, y_train)
toc_bwd = time()


sfs_backward.get_support(indices = True)
print(toc_bwd - tic_bwd)
print(sfs_backward.get_support(indices = True))