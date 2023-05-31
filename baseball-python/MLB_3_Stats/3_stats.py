import pandas as pd
import numpy as np
import plotly.graph_objects as go
import plotly.express as px
from sklearn.datasets import load_iris
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt

df = pd.read_csv(r'C:\Users\ninte\Downloads\mlb_team_stats.csv', encoding = "ISO-8859-1")

# fig = px.scatter_3d(df, x='outs_above_average', y='p_woba', z='woba', hover_data=['team'])
# fig.update_layout(title='MLB Teams')
# fig.show()

fig = plt.figure()
ax = plt.axes(projection='3d')

x=df['outs_above_average']
y=df['p_woba']
z=df['woba']


plot_sct = ax.scatter(x, y, z, c=df['league'])

ax.set_title("3D Scatter plot")
ax.set_xlabel('OAA')
ax.set_ylabel('Pitcher wOBA')
ax.set_zlabel('Hitter wOBA')

plt.show()