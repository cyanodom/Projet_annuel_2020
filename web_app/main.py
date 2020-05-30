from flask import Flask, render_template, request
from flask import jsonify
import subprocess
# from flask_cors import CORS
import sys

app = Flask(__name__, static_folder="client/static", template_folder="client")
# CORS(app)


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/upload_first_time', methods=['POST'])
def upload_first_time():
    graphe = request.files["graphe"]
    graphe.save("./data/graphe.txt")
    file_object = open("./data/graphe.txt", "r")
    graph_data = {}
    lines = file_object.readlines()
    file_object.close()
    nodes = lines[1].replace('\n', '').split(' ')
    nodes = [int(node) for node in nodes]
    edges = [list(eval(edge.replace('\n', ''))) for edge in lines[3:]]
    graph_data['nodes'] = nodes
    graph_data['edges'] = edges
    # res = subprocess.check_output(["cat", "./data/graphe.txt"])
    return jsonify(graph_data)
    # return render_template('resultat.html', result=res)


@app.route('/upload_steiner_result', methods=['GET'])
def upload_steiner_result():
    res = subprocess.check_output(["java", "-jar", "./services/steiner_project.jar", "./data/graphe.txt"]).decode(sys.stdout.encoding).strip()
    steiner_result = {}
    res = res.split('\n')
    nb_terminaux = int(res[0].split(':')[1])
    nodes = res[1:nb_terminaux + 1]
    nodes = [int(node) for node in nodes]
    nb_arcs = int(res[nb_terminaux + 1].split(':')[1])
    edges = res[nb_terminaux + 2:nb_terminaux + nb_arcs + 2]
    edges = [list(eval(edge)) for edge in edges]
    poids = int(res[-2].split(':')[1])
    penality = int(res[-1].split(':')[1])
    steiner_result['nodes'] = nodes
    steiner_result['edges'] = edges
    steiner_result['poids'] = poids
    steiner_result['penality'] = penality

    added_nodes = []
    for edge in edges:
        added_nodes.append(edge[0])
        added_nodes.append(edge[1])
    added_nodes = list(set(added_nodes).difference(set(nodes)))
    steiner_result['added_nodes'] = added_nodes

    return jsonify(steiner_result)


if __name__ == "__main__":
    app.run()
